package market.project;

import java.io.File;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
/**
 * This program is to find the highest investment into multiple companies on the market and get the volatility as low as possible.
 * @author Corentin Pinato (14739811)
 * @version 1.0
 * @date 9 May 2016 
 *
 */
public class Volatility 
{
	public static String path = "C:\\Users\\Corentin\\Documents\\Eclipse\\textFiles\\StockData.txt";
	
	public static final double maxInvest = 19000;
	
	public static double investement = 0;
	
	public static void main(String args[])
	{
		String names[] = getCompanies();
		double stds[] = getStdDev();
		double prices[] = getPrices();
		
		Company companies[] = new Company[names.length];
		
		//This fills the array of companies data.
		for(int i = 0; i < names.length; i++)
		{
			companies[i] = new Company(names[i], stds[i], prices[i], i);
		}
		
		companies = invest(companies);	
		Company invested[] = getInvested(companies);
			
		//Company invested [] = getLowest(companies);
		
/*		for(int i = 0; i < invested.length; i++)
			System.out.println(invested[i].name+" | "+invested[i].std+" | "+invested[i].price+" | "+invested[i].invested);*/
		System.out.println(investement+" invested out of "+maxInvest);
		System.out.println("The volatility is: "+getVolatility(companies,invested));
		
		print(companies);
		
	}
	//This method gets the 22 companies with the lowest volatility.
	public static Company[] getLowest(Company input[])
	{
		Company companies[] = copy(input);
		Company temp[] = copy(input);
		Company minStd[] = new Company[22];
		
		for(int i = 0; i < minStd.length; i++)
		{
			double min = Integer.MAX_VALUE;
			Company minIndex = new Company("",0,0,0);
			
			for(int n = 0; n < companies.length; n++)
			{
				if(companies[n].std < min)
				{
					min = companies[n].std;
					minIndex = temp[n];
				}
			}
			minStd[i] = minIndex;
			companies[minStd[i].index].std = Integer.MAX_VALUE;
		}
		return minStd;
	}
	//this method copy's a Company array.
	public static Company[] copy(Company in[])
	{
		Company out[] = new Company[in.length];
		for(int i = 0; i < in.length; i++)
		{
			out[i] = new Company(in[i].name, in[i].std, in[i].price, in[i].index);
		}
		return out;
	}
	//This method prints out the String of investments.
	public static void print(Company companies[])
	{
		String ans = new String();
		for(int i = 0; i < companies.length; i++)
			ans += companies[i].invested + String.valueOf((char)9);
		ans = ans.substring(0,ans.length()-1);
		System.out.println(ans);
	}
	//This computes the volatility of a final investment.
	public static double getVolatility(Company companies[], Company invested[])
	{
		double data[][] = dataToArray();
		double sum = 0;
		double div = 0;
		
		double change[] = new double[data.length];
		
		for(int i = 0; i<data.length;i++)
		{
			sum = 0; 
			div = 0;
			for(int j = 0; j < invested.length; j++)
			{
				sum += (data[i][invested[j].index]*invested[j].price);
				div += invested[j].price;
			}
			change[i] = sum/div;
		}
		
		sum = 0;
		for(int i = 0; i < invested.length;i++)
		{
			sum += change[i];
		}
		double mean = sum/data.length;
		
		sum = 0;
		for(int i = 0; i < data.length; i++)
		{
			sum += Math.pow(change[i]-mean,2);
		}
		double volatility = Math.sqrt(sum/data.length);
		return volatility;
	}
	//This method invests into companies.
	public static Company[] invest(Company companies[])
	{
		Company minStd[] = getLowest(companies);
		double volatility = Integer.MAX_VALUE;
		
		for(int i = 0; i < 5; i++)
		{
			
			companies[minStd[0].index].invested++;
			double lowest = getVolatility(companies, getInvested(companies));
			Company best = new Company();
			boolean doit = false;
			for(int j = 0; j < minStd.length; j++)
			{
				
				if(investement + minStd[j].price < maxInvest)
				{
					companies[minStd[j].index].invested++;
					double vol = getVolatility(companies, getInvested(companies));
					
					if(lowest > vol)
					{
						doit = true;
						lowest = vol;
						best = companies[minStd[j].index];
					}
					companies[minStd[j].index].invested--;
					
				}

			}
			if(doit)
			{
				investement += companies[best.index].price;
			    companies[best.index].invested++;
			    volatility = lowest;
			}
			
		}
		return companies;
	}
	//This method finds out which companies have been invested.
	public static Company[] getInvested(Company companies[])
	{
		int count = 0;
		for(int i = 0; i < companies.length; i++)
		{
			if(companies[i].invested != 0)
				count++;
		}
		
		Company invested[] = new Company[count];
		count = 0;
		for(int i = 0; i < companies.length; i++)
			if(companies[i].invested != 0)
				invested[count++] = companies[i];
		return invested;
	}

	//This method gets the price of each company share.
	public static double[] getPrices()
	{
		String prices = "€5,108	€1,585	€33,384	€4,071	€2,394	€6,308	€3,153	€3,731	€2,923	€5,056	€3,870	€2,613	€3,448	€1,280	€4,367	€4,451	€8,414	€2,992	€2,446	€3,494	€3,149	€1,559	€2,916	€4,637	€1,256	€711	€5,579	€5,523	€5,270	€20,949	€3,694	€6,615	€5,035	€12,159	€7,703	€9,021	€5,414	€4,646	€6,767	€6,004	€12,393	€2,600	€3,625	€5,075	€29,762	€7,043	€1,098	€5,673	€5,939	€2,559	€2,982	€10,973	€8,482	€4,292	€7,092	€3,396	€10,850	€2,480	€18,333	€3,827	€5,579	€3,182	€2,680	€3,345	€723	€5,771	€10,242	€4,277	€2,155	€2,398	€4,395	€5,043	€10,333	€5,998	€2,539	€2,774	€2,839	€3,580	€6,088	€3,127	€13,684	€2,909	€7,672	€5,212	€2,672	€4,189	€8,728	€6,333	€3,401	€2,463	€5,504	€10,331	€1,838	€1,811	€4,686	€5,179	€1,674	€6,317	€5,985	€5,256	€7,420	€4,623	€3,218	€962	€3,735	€3,654	€1,499	€2,537	€3,284	€3,497	€7,480	€8,078	€2,379	€3,673	€9,676	€4,474	€5,042	€8,026	€1,643	€1,104	€2,578	€5,787	€1,140	€5,429	€3,847	€4,167	€7,291	€2,005	€6,269	€6,616	€3,338	€4,842	€4,625	€5,176	€5,145	€5,931	€4,360	€7,643	€3,274	€5,520	€4,925	€3,387	€3,635	€5,162	€2,761	€4,963	€5,332	€10,161	€2,040	€5,770	€5,211	€5,396	€1,445	€4,921	€6,112	€8,807	€3,853	€5,096	€4,249	€1,348	€3,509	€4,756	€5,230	€9,519	€3,986	€11,316	€950	€2,045	€2,969	€6,374	€1,223	€3,326	€6,256	€3,603	€4,300	€4,017	€13,306	€4,449	€654	€3,748	€1,321	€7,137	€1,778	€2,098	€3,480	€1,767	€2,531	€1,056	€52,103	€5,174	€1,761	€13,220	€1,729	€15,288	€5,005	€4,549	€4,177	€625	€756	€4,707	€3,350	€3,485	€7,413	€2,587	€5,039	€4,079	€5,690	€5,624	€6,611	€3,502	€1,483	€2,881	€4,254	€5,567	€1,685	€5,459	€8,070	€16,788	€12,698	€6,178	€1,729	€2,075	€5,110	€2,844	€1,205	€4,552	€2,844	€37,865	€11,534	€5,433	€2,218	€2,004	€4,023	€3,418	€1,701	€4,335	€6,229	€3,197	€900	€3,893	€4,606	€5,156	€806	€3,413	€3,865	€6,157	€3,368	€3,196	€2,399	€4,884	€4,153	€2,248	€1,851	€9,842	€5,233	€8,306	€3,122	€3,408	€3,262	€7,394	€2,828	€2,274	€722	€3,463	€3,307	€1,151	€2,744	€2,861	€31,255	€3,361	€1,183	€2,585	€7,988	€3,502	€8,242	€3,764	€3,679	€4,179	€4,727	€2,966	€9,125	€2,352	€2,477	€6,982	€3,245	€3,135	€2,320	€2,441	€4,452	€8,292	€783	€5,239	€3,151	€1,487	€2,497	€4,423	€2,468	€2,552	€3,824	€5,354	€5,026	€26,799	€6,925	€1,893	€4,444	€6,521	€7,683	€2,428	€7,244	€5,411	€4,389	€3,304	€3,837	€1,579	€1,531	€3,165	€2,618	€3,529	€4,608	€3,235	€6,621	€9,957	€2,836	€1,213	€1,928	€4,909	€3,876	€3,769	€52,480	€16,558	€3,255	€6,554	€1,907	€2,858	€5,979	€2,076	€8,771	€777	€2,683	€3,357	€5,589	€5,753	€4,106	€1,755	€8,756	€2,507	€5,981	€10,783	€2,054	€10,405	€8,993	€5,569	€5,548	€3,317	€3,916	€618	€2,629	€4,640	€13,143	€8,440	€8,269	€3,972	€5,610	€1,667	€2,900	€1,255	€4,626	€1,496	€3,892	€3,615	€1,610	€2,547	€2,241	€7,213	€8,314	€7,241	€7,283	€8,467	€1,585	€6,072	€4,263	€3,692	€11,121	€1,480	€9,122	€4,938	€2,584	€4,617	€4,396	€2,181	€905	€6,953	€4,380	€2,191	€5,812	€1,958	€2,898	€2,813	€4,240	€6,091	€1,715	€4,691	€1,518	€4,551	€2,604	€1,787	€7,611	€2,591	€319	€4,192	€6,405	€5,808	€5,555	€1,905	€2,262	€1,808	€3,489	€3,172	€2,376	€2,355	€5,138	€2,478	€10,179	€6,914	€2,892	€2,473	€8,529	€7,087	€10,641	€4,957	€2,245	€3,934	€8,678	€3,388	€4,899	€3,396	€4,031	€9,763	€3,569	€2,953	€2,707	€858	€7,802	€1,040	€7,747	€3,424	€2,302	€5,058	€6,985	€39,540	€1,908	€2,090	€3,371	€12,818	€4,604	€2,258	€2,133	€3,507	€7,741	€3,801	€1,016	€1,545	€5,434	€2,426	€6,387	€1,340";
		prices = prices.replace("€", "");
		prices = prices.replace(",", "");

		String array[] = prices.split(String.valueOf((char)9));

		double ans[] = new double[array.length];

		for(int i = 0; i < array.length; i++)
			ans[i] = Double.parseDouble(array[i]);

		return ans;
	}
	//This method gets the standard deviation of each company.
	public static double[] getStdDev()
	{
		double data[][] = dataToArray();
		double ans [] = new double[data[0].length];
		for(int n = 0; n < data[0].length; n++)
		{
			double sum = 0;
			for(int i = 0; i < data.length; i++)
			{
				sum += data[i][n];
			}
			double mean = sum/data.length;

			sum = 0;
			for(int i = 0; i < data.length; i++)
			{
				sum += Math.pow(data[i][n]-mean,2);
			}
			double newMean = sum/data.length;

			ans[n] = Math.sqrt(newMean);
		}
		return ans;
	}
	//This method gets the data contained in the spreadsheet.
	public static double[][] dataToArray()
	{
		int rows = 0;
		int columns = 0;
		double data[][] = new double[1][1];

		try {

			File file = new File(path);
			Scanner sc = new Scanner(file);

			String strArr[] = sc.nextLine().split(String.valueOf((char)9));// separate everything with 5 spaces.
			columns = strArr.length-1;//-1 so that it does not try to parse a date to double.

			sc.close();
			sc = new Scanner(file);

			while(sc.hasNextLine())
			{
				rows++;
				sc.nextLine();
			}

			rows = 882;
			rows--; // -1 so that it does not try to parse text in double.

			sc.close();
			sc = new Scanner(file);

			data = new double[rows][columns];

			sc.nextLine();

			for(int row = 0; row < rows; row++)
			{
				String str[] = sc.nextLine().split(String.valueOf((char)9));
				for(int col = 0; col < columns; col++)
				{
					data[row][col] = Double.parseDouble(str[col+1]);
				}
			}
			sc.close();

		} catch (Exception e) {System.out.println(e);}
		return data;
	}
	//This method gets the company names.
	public static String[] getCompanies()
	{
		String ans[] = new String[1];
		try {

			File file = new File(path);
			Scanner sc = new Scanner(file);

			ans = sc.nextLine().split(String.valueOf((char)9));
			sc.close();

		} catch (Exception e) {

		}
		return Arrays.copyOfRange(ans, 1, ans.length);
	}

}
class Company
{
	public String name = new String();
	public double std = 0;
	public double price = 0;
	public int invested = 0;
	public int index = 0;
	
	public Company(){}
	public Company(String name, double std, double price, int index)
	{
		this.name = name;
		this.std = std;
		this.price = price;
		this.invested = 0;
		this.index = index;
	}
}
