package Lab07;

public class Koreanic extends Language{
	public Koreanic(String name, int numSpeakers) {
		super(name, numSpeakers,"Korean peninsula(South Korea, North Korea, Juju) and several prefectures of China","subject-object-verb");
		if(name.contains("Jeju")) {
			regionsSpoken="Jeju island";
		}
	}
}
