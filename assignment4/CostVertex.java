class CostVertex implements Comparable<CostVertex> {
	int name, cost;

	public CostVertex(int _name, int _cost) {
		name = _name;
		cost = _cost;
	}

	public int compareTo(CostVertex other) {
		return (cost < other.cost) ? -1 : (cost > other.cost) ? 1 : 0;
	}
}