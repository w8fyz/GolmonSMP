package sh.fyz.golmonsmp.utils.data;

import java.lang.reflect.Type;
import java.util.Arrays;

import com.google.common.reflect.TypeToken;

import sh.fyz.golmonsmp.account.Account;
import sh.fyz.golmonsmp.account.json.AccountJson;

@SuppressWarnings("serial")
public enum DataType {
	
	
	ACCOUNTS("players", Account.class, new TypeToken<Account>() {}.getType(), new AccountJson());
	
	private String src;
	private Class<?> dataClass;
	private Type token;
	private Object customGson;
	
	private DataType(String src, Class<?> dataClass, Type token, Object customGson) {
		this.src = src;
		this.token = token;
		this.dataClass = dataClass;
		this.customGson = customGson;
	}
	
	public Object getCustomGson() {
		return customGson;
	}
	
	public boolean haveCustomGson() {
		return customGson != null;
	}
	
	public Type getToken() {
		return token;
	}
	
	public Class<?> getDataClass() {
		return dataClass;
	}
	
	public String getSrc() {
		return src;
	}

	public static DataType getType(Class<?> c) {
		return Arrays.stream(values()).filter(e -> e.getDataClass().toString().equals(c.toString())).findAny().orElse(null);
	}

}
