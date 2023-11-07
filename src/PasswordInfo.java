public class PasswordInfo {
    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String url;
        private String id;
        private String password;

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public PasswordInfo build() {
            return new PasswordInfo(this);
        }
    }

    private String url; // Primary Key
    private String id;
    private String password;

    PasswordInfo(String url, String id, String pw) {
        this.url = url;
        this.id = id;
        this.password = pw;
    }

    PasswordInfo(Builder builder) {
        this.url = builder.url;
        this.id = builder.id;
        this.password = builder.password;
    }

    @Override
    public String toString() {
        return "" + url + ", " + id + ", " + password;
    }

    public String getUrl() {
        return url;
    }

    public void setName(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String pw) {
        this.password = pw;
    }
}
