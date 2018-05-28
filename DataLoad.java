

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DataLoad {

	public static void main(String[] args) throws Exception {
//		https://www.quantopian.com/posts/enhancing-short-term-mean-reversion-strategies-1
		String part1 = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=";
		String part2 = "&outputsize=full&apikey=EI6Z3J2CCXT2LJR0&datatype=csv";
		Scanner userInput = new Scanner(System.in);
		String tickerSymbol;
		ArrayList<Stock> stockList = new ArrayList<Stock>();
		ArrayList<String> SP500 = new ArrayList<String>();
		
//		System.out.println("Choose one of the options: ");
//		System.out.println("1) View stocks portfolio");
//		if (userInput.nextLine() == "1")
//			System.out.println(stockList);

		tickerSymbol="GE";
		//		System.out.println("Enter Company Ticker: ");
		//		String str = userInput.nextLine();
		//		userInput.close();
		//		

		//Get data with API
		ArrayList<String> rawData = getDoc(part1 + tickerSymbol + part2);
		Stock s = new Stock(tickerSymbol, rawData, 50);
		stockList.add(s);
		System.out.println("50 Moving Average: " + s.computeMovingAverage(50));
		System.out.println("200 Moving Average: " + s.computeMovingAverage(200));
		Holdings h = new Holdings(stockList);
		h.doTrades();
	}

	//returns an ArrayList of data for a company
	public static ArrayList<String> getDoc(String urlToRead) {

		URL url;
		HttpURLConnection conn;
		BufferedReader rd;
		String line;
		ArrayList<String> data = new ArrayList<String>(); 
		try {
			url = new URL(urlToRead);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			while ((line = rd.readLine()) != null) {
				data.add(line+"\n");
			}
			rd.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
}