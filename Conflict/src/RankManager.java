
public class RankManager {
	private int rank;
	
	public RankManager() {
		this.rank = 0;
	}
	
	public int getRank() {
		return this.rank;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public int next() {
		this.rank++;
		return getRank();
	}

}
