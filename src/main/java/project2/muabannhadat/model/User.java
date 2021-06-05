package project2.muabannhadat.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "user_name")
    @Length(min = 5, message = "*Tên đăng nhập tối thiểu 5 kí tự")
    @NotEmpty(message = "*Vui lòng nhập tên đăng nhập")
    private String userName;

    @Column(name = "password")
    @Length(min = 5, message = "*Mật khẩu nhập tối thiểu 5 kí tự")
    @NotEmpty(message = "*Vui lòng nhập mật khẩu")
    private String password;

    @NotEmpty(message = "*Vui lòng cung cấp số điện thoại")
    @Column(name = "phone")
    private String phone;


    @Column(name = "active")
    private Boolean active;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;


}
