package ch.epfl.cs107.play.signal.logic;




public class MultipleAnd extends LogicSignal{

	private Logic[] p;

	/**
	 * Constructor
	 * @param p (Logic) : signals
	 */
	public MultipleAnd(Logic...p) {
		this.p = new Logic[p.length];
		for (int i = 0 ; i < p.length ; ++i) {
			this.p[i] = p[i];
		}
	}

	@Override
	public boolean isOn() {
		for(Logic logic : p) {
			if(!logic.isOn()) {
				return false;
			} 
		}
		return true;
	}
}