package com.kronos.io;

import java.io.Serializable;

public interface ResourceIdentifier extends Serializable {

	public String getBasePath();

	public String getNameid();

}
