package service;

import jakarta.validation.ValidationException;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.Collection;
import java.util.List;
@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public User addUser(User user){
        if (userRepository.existsByEmail(user.getEmail())){
            throw new ValidationException("Bu email ile kayıtlı kullanıcı var.");
        }
        userRepository.save(user);
        return user;
    }

    public User addComment(User user){
        if ("employer".equals(user.getRole())){
            throw new ValidationException("Yorum boş bırakılamaz.");
        }
        userRepository.save(user);
        return user;
    }

    public User addRate(User user){
        if("employer".equals(user.getRole())){
            throw new ValidationException("0 ile 5 arasında puan verin.");
        }

        if (user.getRole()==null || user.getRate() < 0 || user.getRate() > 5) {
            throw new ValidationException("Puan 0 ile 5 arasında olmalıdır.");
        }
        userRepository.save(user);
        return user;
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı, ID: " + id));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        return List.of(new SimpleGrantedAuthority(user.getRole()));
    }

}
