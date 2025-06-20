package mudkipboy7.game;

public class CharacterSettings {
	private Integer texId;
	private Float distAdderMod;

	public CharacterSettings(Integer texId, Float distAdderMod) {
		this.texId = texId;
		this.distAdderMod = distAdderMod;
	}

	public Integer getTexId() {
		return texId;
	}

	public Float getDistAdderMod() {
		return distAdderMod;
	}
}
