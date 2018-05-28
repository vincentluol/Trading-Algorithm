import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;


public class Stock {

	ArrayList<Stock> dailyStockData = new ArrayList<Stock>();
	ArrayList<Double> closeData = new ArrayList<Double>();
	ArrayList<String> data = new ArrayList<String>();
	private String time;
	private double open;
	private double high;
	private double low;
	private double close;
	private double volume;
	private int numShares;
	

	//builds stock objects and adds it to dailyStockData
	public Stock(String s, ArrayList<String> a, Integer numShares){
		this.numShares = numShares;
		data = a;
		for (String line: data){
			String[] fields = new String[5];
			fields = line.split(",");
			if (!(line.startsWith("timestamp")))
			{	
				this.time = fields[0];
				this.open = Double.parseDouble(fields[1]);
				this.high = Double.parseDouble(fields[2]);
				this.low = Double.parseDouble(fields[3]);
				this.close = Double.parseDouble(fields[4]);
				this.volume = Double.parseDouble(fields[5]);
				dailyStockData.add(this);	
			}
		}
	}
	
	//returns number of shares of a stock
	public int getNumShares(){
		return numShares;
	}
	
	//returns daily data for a stock
	public ArrayList<Stock> getDailyStockData(){
		return dailyStockData;
	}

	//returns closing price of a stock
	public double getClose(){
		return close;
	}

	//prints data out for a stock
	public String toString(){
		return (time+" , "+open+" , "+high+" , "+low+" , "+close+" , "+volume);
	}

	//returns list of closing prices for a stock
	public ArrayList<Double> getCloseData(){
		for (Stock stock: dailyStockData){
			closeData.add(stock.getClose());
		}
		return closeData;
	}
	
	//calculates moving average for a stock within a specified time
	public double computeMovingAverage(int n){
		double total = 0;
		for (int i = 0; i < n; i++){
			total += closeData.get(i);
		}
		return total/n;
	}
	
}