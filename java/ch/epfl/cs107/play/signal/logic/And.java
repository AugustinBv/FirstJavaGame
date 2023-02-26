package ch.epfl.cs107.play.signal.logic;




public class And extends LogicSignal{

	private Logic p;
	private Logic q;

	/**
	 * And constructor
	 * @param p (Logic) : a signal
	 * @param q (Logic) : a signal
	 */
	public And(Logic p, Logic q) {
		this.p = p;
		this.q = q;
	}

	@Override
	public boolean isOn() {
		if (p != null && q != null) {
			if (p.isOn() && q.isOn()) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

}