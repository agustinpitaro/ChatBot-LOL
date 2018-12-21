package Utilities;
import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;

//Devuelve datos de cuenta de usuario. (Para futuras extensiones del proyecto)
public class SummonerExample {

	public static void main(String[] args) throws RiotApiException {
		ApiConfig config = new ApiConfig().setKey("RGAPI-bf66375c-0024-4e30-b279-7b1c2a5a414f");
		RiotApi api = new RiotApi(config);

		Summoner summoner = api.getSummonerByName(Platform.LAS,"KidSO");
		System.out.println("Name: " + summoner.getName());
		System.out.println("Summoner ID: " + summoner.getId());
		System.out.println("Account ID: " + summoner.getAccountId());
		System.out.println("Summoner Level: " + summoner.getSummonerLevel());
		System.out.println("Profile Icon ID: " + summoner.getProfileIconId());
	}
}