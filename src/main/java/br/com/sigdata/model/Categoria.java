package br.com.sigdata.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import br.com.sigdata.tenancy.TenancyInterceptor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "fin_categoria")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@FilterDef(name = "tenant", parameters = { @ParamDef(name="tenantid", type="string") })
@Filter(name = "tenant", condition = "tenant_id = :tenantid")
public class Categoria {
	
	@EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "tenant_id", nullable = false)
    private String tenantId;
    
    @NotEmpty
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotEmpty
    @Column(name = "codigo", nullable = false)
    private String codigo;

    @PrePersist
    @PreUpdate
    private void tenantId() {
        String tenantId = TenancyInterceptor.getTenantId();
        if (tenantId != null) {
            this.tenantId = tenantId;
        }
    }

}
