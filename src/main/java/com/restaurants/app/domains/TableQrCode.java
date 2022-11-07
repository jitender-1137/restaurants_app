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
@Table(name = "table_qr_code")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class TableQrCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "table_qr_id")
    private Long tableQRId;

    @Column(name = "table_name")
    private String tableName;

    @Column(name = "table_no")
    private Long tableNo;

	@Column(name = "enable", nullable = false)
	private boolean enable;

	@Column(name = "created_at")
	private Long createdAt;

    @Column(name = "updated_at")
    private Long updatedAt;

    @Column(name = "url")
    private String url;

}
