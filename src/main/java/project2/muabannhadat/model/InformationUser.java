package project2.muabannhadat.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Collection;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "information_users")
public class InformationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "infor_id")
    private Long inforId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "fullname")
    @NotEmpty(message = "*Vui lòng cung cấp tên của bạn!!!")
    private String fullName;


    @Column(name = "dob")
    private Date dob;

    @NotEmpty(message = "*Vui lòng cung cấp địa chỉ!!!")
    @Column(name = "address")
    private String address;

    @NotEmpty(message = "*Vui lòng cung cấp số điện thoại!!!")
    @Length(min =10, message = "*Hãy nhập đúng số điện thoại gồm 10 chữ số")
    @Column(name = "phone")
    private String phone;

    @Email(message = "*Nhập đúng định dạng email")
    @NotEmpty(message = "*Vui lòng cung cấp email!!!")
    @Column(name = "email")
    private String email;

    @Column(name = "avatar")
    private String avatar;

}
