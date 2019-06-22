package im.tabis.prod.entity;

import com.alibaba.fastjson.annotation.JSONField;
import im.tabis.core.entity.AbstractEntity;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Date;

/**
 * 
 * @author gg
 * @desc :模板类
 */
@Entity
@Table(name = "dic_demo")
@DynamicUpdate()
@DynamicInsert()
@Data
public class Demo implements AbstractEntity<BigInteger> {

	private static final Integer MAX_TITLE_LENGTH = 18;

	@Id
	@Column(length = 20, name = "ID", nullable = false, unique = true, updatable = false)
	private BigInteger id;
	/** 标题 . */
	@NotEmpty
	@NotBlank
	@Length(min=1,max = 200)
	@Column(name = "TITLE", nullable = false,updatable = false, length=200)
	private String title;

	@Column(name = "URL", nullable = false)
	private String url;

	@Column(name = "TYPE", nullable = false)
	private String type;
	/** 是否发布. */
	@NotNull
	@Column(name = "IS_PUB", nullable = false)
	private Boolean isPub=Boolean.FALSE;

	/** 是否置顶. */
	@NotNull
	@Column(name = "IS_TOP",nullable = false)
	private Boolean isTop=Boolean.FALSE;

	/** 点击数 . */
	@Column(name = "HITS", nullable = false, length=20)
	private Long hits=0L;

	@Column(name = "IS_DELETED", nullable = false, length=20)
	private Boolean isDeleted=Boolean.FALSE;

	@Column(length = 32, name = "USER_ID", nullable = false, updatable = false)
	private BigInteger userId;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "CREATE_TIME", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "PUSH_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date pushTime;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss", serialize = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "MODIFY_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyTime;
	@PrePersist
	protected void onCreate() {
		createTime = new Date();
		modifyTime = new Date();
		if(pushTime==null){
			pushTime = new Date();
		}
	}
	@PreUpdate
	protected void onUpdate() {
		modifyTime = new Date();
		if(isPub){
			pushTime = new Date();
		}
	}

	@Transient
	public String getShortTitle() {
		if (!StringUtils.isBlank(title) && (title.length() > MAX_TITLE_LENGTH)) {
			return title.substring(0, MAX_TITLE_LENGTH) + "...";
		}
		return title;
	}
}
