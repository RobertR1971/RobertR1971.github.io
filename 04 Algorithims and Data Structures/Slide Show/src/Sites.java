
/**
 * Robert Robinson
 * CS 499 Milestone 3
 * November 24, 2019
 *  
 */

//Create class to hold data used by slide show

public class Sites {
	
	private int rank;
	private String name;
	private String details;

	//Default values 
	public Sites()
	{
		this.rank = 0;
		this.name = null;
		this.details = null;
	}
	
	
	public Sites(int rank, String name, String details)
	{
		this.rank = rank;
		this.name = name;
		this.details = details;
	}

	public int getRank() {
		return rank;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return details;
	}
	
	public void setDetails(String details) {
		this.details = details;
	}
}