package com.cognixia.jump.maven.libraryproject.model;

public class Patron {
	
        int patronId;
		String firstName;
		String lastName;
		String userName;
		String password;
		boolean accountFrozen;
		
		
		public Patron(String firstName, String lastName, String userName, String password, boolean accountFrozen) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.userName = userName;
			this.password = password;
			this.accountFrozen = accountFrozen;
		}
		public Patron(int patronId, String firstName, String lastName, String userName, String password,
				boolean accountFrozen) {
			super();
			this.patronId = patronId;
			this.firstName = firstName;
			this.lastName = lastName;
			this.userName = userName;
			this.password = password;
			this.accountFrozen = accountFrozen;
		}
		public Patron(int i, String firstName2, String lastName2, String userName2, String passWord2, int patronId2) {
			// TODO Auto-generated constructor stub
		}
		public int getPatronId() {
			return patronId;
		}
		public void setPatronId(int patronId) {
			this.patronId = patronId;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public boolean isAccountFrozen() {
			return accountFrozen;
		}
		public void setAccountFrozen(boolean accountFrozen) {
			this.accountFrozen = accountFrozen;
		}
		@Override
		public String toString() {
			return "Patron [patronId=" + patronId + ", firstName=" + firstName + ", lastName=" + lastName + ", userName="
					+ userName + ", password=" + password + ", accountFrozen=" + accountFrozen + ", getPatronId()="
					+ getPatronId() + ", getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName()
					+ ", getUserName()=" + getUserName() + ", getPassword()=" + getPassword() + ", isAccountFrozen()="
					+ isAccountFrozen() + "]";
		}
		
		
	
}
