/**
 * 
 */
package com.kronos.plugin.info;

import java.util.Arrays;

/**
 * 
 */
public class AuthorInfo {
	String name, uid;
	String version;

	String[] dependencies;
	boolean softDeps = false;

	public AuthorInfo(String name, String uid, String version, String... dependencies) {
		this.name = name;
		this.uid = uid;
		this.version = version;
		this.dependencies = dependencies;
	}

	public AuthorInfo(String name, String uid, String version, String[] dependencies, boolean softDeps) {
		this.name = name;
		this.uid = uid;
		this.version = version;
		this.dependencies = dependencies;
		this.softDeps = softDeps;
	}

	public static AuthorInfo create(String name, String uid, String version, String... dependencies) {
		return new AuthorInfo(name, uid, version, dependencies);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the dependencies
	 */
	public String[] getDependencies() {
		return dependencies;
	}

	/**
	 * @param dependencies the dependencies to set
	 */
	public void setDependencies(String[] dependencies) {
		this.dependencies = dependencies;
	}

	/**
	 * @return the softDeps
	 */
	public boolean isSoftDeps() {
		return softDeps;
	}

	/**
	 * @param softDeps the softDeps to set
	 */
	public void setSoftDeps(boolean softDeps) {
		this.softDeps = softDeps;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AuthorInfo [");
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (uid != null) {
			builder.append("uid=");
			builder.append(uid);
			builder.append(", ");
		}
		if (version != null) {
			builder.append("version=");
			builder.append(version);
			builder.append(", ");
		}
		if (dependencies != null) {
			builder.append("dependencies=");
			builder.append(Arrays.toString(dependencies));
			builder.append(", ");
		}
		builder.append("softDeps=");
		builder.append(softDeps);
		builder.append("]");
		return builder.toString();
	}

}
