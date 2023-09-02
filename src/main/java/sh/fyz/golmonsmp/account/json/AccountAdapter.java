package sh.fyz.golmonsmp.account.json;

import com.google.common.reflect.TypeToken;
import com.google.gson.*;
import sh.fyz.golmonsmp.account.Account;
import sh.fyz.golmonsmp.account.Home;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AccountAdapter implements JsonSerializer<Account>, JsonDeserializer<Account> {

	@SuppressWarnings("serial")
	@Override
	public Account deserialize(JsonElement json, Type type, JsonDeserializationContext context)
			throws JsonParseException {
		JsonObject jsonObject = json.getAsJsonObject();

		UUID uuid = UUID.fromString(jsonObject.get("uuid").getAsString());

		String pronouns;
		if (jsonObject.get("pronouns") == null) {
			pronouns = "";
		} else {
			pronouns = jsonObject.get("pronouns").getAsString();
		}

		List<Home> homes;
		if (jsonObject.get("homes") == null) {
			homes = new ArrayList<>();
		} else {
			homes = new Gson().fromJson(jsonObject.get("homes"), new TypeToken<List<Home>>() {
			}.getType());
		}

		long timestampLastTP = jsonObject.get("timestampLastTP").getAsLong();


		int deaths = jsonObject.get("deaths").getAsInt();
		int color = jsonObject.get("color").getAsInt();
		String discordID = jsonObject.get("discordID").getAsString();
		int playCount = jsonObject.get("playCount").getAsInt();
		boolean scoreboard = jsonObject.get("scoreboard").getAsBoolean();
		
		return new Account(uuid, pronouns, homes, timestampLastTP, deaths, color, discordID, playCount, scoreboard);
	}

	@Override
	public JsonElement serialize(Account account, Type type, JsonSerializationContext context) {
		JsonObject result = new JsonObject();

		UUID uuid = account.getUUID();
		String pronouns = account.getPronouns();
		List<Home> homes = account.getHomes();
		long timestampLastTP = account.getLastTP();
		int deaths = account.getDeaths();
		int color = account.getColor();
		String discordID = account.getDiscordID();
		int playCount = account.getPlayCount();
		
		result.add("uuid", context.serialize(uuid));
		result.add("pronouns", context.serialize(pronouns));
		result.add("homes", context.serialize(homes));
		result.add("timestampLastTP", context.serialize(timestampLastTP));
		result.add("deaths", context.serialize(deaths));
		result.add("color", context.serialize(color));
		result.add("discordID", context.serialize(discordID));
		result.add("playCount", context.serialize(playCount));
		result.add("scoreboard", context.serialize(account.isScoreboardActive()));
		
		return result;
	}

}
