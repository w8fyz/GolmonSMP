package sh.fyz.golmonsmp.account.json;

import com.google.common.reflect.TypeToken;
import com.google.gson.*;
import org.bukkit.Location;
import sh.fyz.golmonsmp.account.Home;
import sh.fyz.golmonsmp.account.HomeLocation;

import java.lang.reflect.Type;

public class HomeAdapter implements JsonSerializer<Home>, JsonDeserializer<Home> {

	@Override
	public Home deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
		JsonObject jsonObject = json.getAsJsonObject();

		String name = jsonObject.get("name").getAsString();
		@SuppressWarnings("serial")
		Location loc = new Gson().fromJson(jsonObject.get("loc"), new TypeToken<Location>() {
		}.getType());

		return new Home(name, new HomeLocation(loc.getWorld().getUID(), loc.getX(), loc.getY(), loc.getZ(),
				loc.getYaw(), loc.getPitch()));
	}

	@Override
	public JsonElement serialize(Home home, Type type, JsonSerializationContext context) {
		JsonObject result = new JsonObject();

		String name = home.getName();
		HomeLocation loc = home.getLocation();

		result.add("name", context.serialize(name));
		result.add("loc", context.serialize(loc));

		return result;
	}

}
