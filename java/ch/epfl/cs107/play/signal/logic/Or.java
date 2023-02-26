package ch.epfl.cs107.play.signal.logic;




public class Or extends LogicSignal{

	private Logic p;
	private Logic q;

	/**
	 * Or constructor
	 * @param p (Logic) : Signal
	 * @param q (Logic) : Signal
	 */
	public Or(Logic p, Logic q) {
		this.p = p;
		this.q = q;
	}

	@Override 
	public boolean isOn() {
		if (p != null && q != null) {
			if (p.isOn() || q.isOn()) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

}