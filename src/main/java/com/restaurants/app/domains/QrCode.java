package com.restaurants.app.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "qr_code")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class QrCode {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "qr_code_id")
	private Long qrCodeId;

	@Column(name = "table_name")
	private String tableName;

	@Column(name = "table_no")
	private String tableNo;

	@Column(name = "enable")
	private boolean enable;

	@Column(name = "created_at")
	private String createdAt;

	@Column(name = "updated_at")
	private String updatedAt;

}
