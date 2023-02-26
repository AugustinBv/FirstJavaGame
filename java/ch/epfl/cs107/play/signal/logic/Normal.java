package ch.epfl.cs107.play.signal.logic;




public class Normal extends LogicSignal{

	private Logic s;

	/**
	 * setter
	 * @param s (Logic) : signal
	 */
	public Normal(Logic s) {
		this.s = s;
	}

	@Override
	public boolean isOn() {
		if (s != null) {
			if (s.isOn()) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}

}