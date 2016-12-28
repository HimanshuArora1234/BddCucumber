package fr.him.bdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {

	private List<User> storage = null;
	
	public UserRepository() {
		storage = new ArrayList<>();
		storage.add(new User(1, "James", 55, "j@startrek.com"));
		storage.add(new User(2, "Luke", 60, "lu@starwars.com"));
		storage.add(new User(3, "Leia", 70, "le@starwars.com"));
	}
	
	public Integer count() {
		return storage.size();
	}

	public Integer create(User u) {
		Integer nextId = storage.stream().max((e1, e2) -> e1.getId() - e2.getId()).get().getId() + 1;
		u.setId(nextId);
		storage.add(u);
		return nextId;
	}

	public User read(int arg1) {
		return storage.stream().filter(e -> e.getId().equals(arg1)).findFirst().orElse(null);
	}

	public List<User> readAll() {
		return storage;
	}

	public void delete(int id) {
		storage.remove(storage.stream().filter(e -> e.getId().equals(id)).findFirst().get());
	}


}
