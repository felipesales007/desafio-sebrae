package br.com.mega.hack.model.dto.user;

import br.com.mega.hack.model.UserApp;

public class UserInfo {
	private final UserResponse user;

	public UserInfo(Builder builder) {
		this.user = builder.user;
	}

	public UserResponse getUser() {
		return user;
	}

	public static class Builder {
		private UserResponse user;

		public Builder withUser(UserApp userApp) {
			this.user = new UserResponse(userApp);
			return this;
		}

		public UserInfo build() {
			UserInfo user = new UserInfo(this);
			return user;
		}

	}

}
