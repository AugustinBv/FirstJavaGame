package ch.epfl.cs107.play.signal.logic;




public class Not extends LogicSignal{

	private Logic s;

	/**
	 * Not Constructor
	 * @param s (Logic) : Signal 
	 */
	public Not(Logic s) {
		this.s = s;
	}

	@Override
	public boolean isOn() {
		if (s != null) {
			if (s.isOn()) {
				return false;
			} else {
				return true;
			}
		}
		return true;
	}

}