import java.util.*;


public class Holdings {

	ArrayList<Stock> dailyStockData = new ArrayList<Stock>();
	HashMap<Stock, Integer> map = new HashMap<Stock, Integer>();

	//stores all stocks and their shares in a map
	public Holdings(ArrayList<Stock> stockList){
		for (Stock s: stockList){
			map.put(s, s.getNumShares());
		}
	}

	//trades stock based on conditions
	public void doTrades(){
		for (Stock s: map.keySet()){
			if (s.computeMovingAverage(50) < s.computeMovingAverage(200)){
				sell(s);
			}
		}
		//buy S&P500 stock with lowest five day returns

	}

	//sells stock by setting numShares to 0
	public void sell(Stock s){
		map.put(s, 0);
	}

	//buys n shares of stock
	public void buy(Stock s, int n){
		map.put(s, n);
	}

}
