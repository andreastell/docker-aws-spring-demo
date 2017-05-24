package se.callistaenterprise.demo.api.controller.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by tell on 2016-09-12.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name = "meta")
@ApiModel(value = "Metadata för API", description = "Håller information om metadata för komponenten som realiserar API")
public class ApiMetadata {
    @ApiModelProperty(value = "Version på komponent.", required = true)
    private final String version;
    @ApiModelProperty(value = "Byggnummer.", required = true)
    private final String buildNumber;
    @ApiModelProperty(value = "Tidpunkt när komponenten byggdes.", required = true)
    private final String buildTimestamp;
}

