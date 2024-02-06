package com.kronos.gameFramework.templates;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.joml.Matrix3dc;
import org.joml.Matrix3fc;
import org.joml.Matrix3x2fc;
import org.joml.Matrix4dc;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Matrix4x3fc;
import org.joml.Quaternionf;
import org.joml.Quaternionfc;
import org.joml.Vector2dc;
import org.joml.Vector2fc;
import org.joml.Vector2ic;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import com.kronos.dynamo.simple.MathLerp;

public class Transform implements Template {
	protected Vector3f position, up, looking;
	Matrix4f trans = new Matrix4f();
	protected float velocity, yaw, pitch, rotatex, rotatey, rotatez;
	List<Transform> children;
	public static final Vector3f no_pos = new Vector3f();

	public Transform(Vector3f position) {
		super();
		this.position = position;
		up = new Vector3f(0, 1, 0);
		looking = position;
		velocity = 0;
		yaw = 0;
		pitch = 0;
		children = new ArrayList<>();

	}

	boolean update = false;

	public void yaw(float ya, float easing) {
		yaw = MathLerp.lerpSmooth(yaw + ya, yaw, easing);
		update = true;
		for (Iterator iterator = children.iterator(); iterator.hasNext();) {
			Transform c = (Transform) iterator.next();
			c.yaw(ya, easing);
		}
	}

	public void pitch(float pi, float easing) {
		pitch = MathLerp.lerpSmooth(pitch + pi, pitch, easing);
		update = true;
		for (Iterator iterator = children.iterator(); iterator.hasNext();) {
			Transform c = (Transform) iterator.next();
			c.pitch(pi, easing);
		}
	}

	public Vector3f getDirection() {
		// Calculate the direction vector based on yaw and pitch
		return new Vector3f((float) (Math.cos(yaw) * Math.cos(pitch)), (float) Math.sin(pitch),
				(float) (Math.sin(yaw) * Math.cos(pitch))).normalize();
	}

	public void moveRight(float distance) {

		Vector3f right = new Vector3f();
		right.set(getDirection()).cross(up).normalize().mul(distance);
		position.add(right);
		for (Iterator iterator = children.iterator(); iterator.hasNext();) {
			Transform c = (Transform) iterator.next();
			c.moveRight(distance);
		}
	}

	public void moveLeft(float distance) {

		Vector3f right = new Vector3f();
		right.set(getDirection()).cross(up).normalize().mul(-distance);
		position.add(right);
		for (Iterator iterator = children.iterator(); iterator.hasNext();) {
			Transform c = (Transform) iterator.next();
			c.moveLeft(distance);
		}
	}

	public void moveForeward(float amnt) {
		Vector3f t = getDirection();
		t.normalize().mul(amnt);
		position.add(t);
		for (Iterator iterator = children.iterator(); iterator.hasNext();) {
			Transform c = (Transform) iterator.next();
			c.moveForeward(amnt);
		}
	}

	public void moveBackward(float amnt) {
		Vector3f t = getDirection();
		t.normalize().mul(amnt);
		position.sub(t);
		for (Iterator iterator = children.iterator(); iterator.hasNext();) {
			Transform c = (Transform) iterator.next();
			c.moveBackward(amnt);
		}
	}

	public void addChild(Transform t) {
		this.children.add(t);

	}

	public void parentTo(Transform t) {
		t.addChild(this);
	}

	@Override
	public void update() {

	}

	@Override
	public boolean requiresUpdate() {
		// TODO Auto-generated method stub
		return update;
	}

	public Vector3f getPosition() {
		return position;
	}

	public Vector3f getUp() {
		return up;
	}

	public Vector3f getLooking() {
		return looking;
	}

	public float getVelocity() {
		return velocity;
	}

	public float getYaw() {
		return yaw;
	}

	public float getPitch() {
		return pitch;
	}

	public boolean isUpdate() {
		return update;
	}

	public float x() {
		return position.x();
	}

	public float y() {
		return position.y();
	}

	public float z() {
		return position.z();
	}

	public Vector3f set(Vector3fc v) {
		return position.set(v);
	}

	public Vector3f set(Vector3dc v) {
		return position.set(v);
	}

	public Vector3f set(Vector3ic v) {
		return position.set(v);
	}

	public Vector3f set(Vector2fc v, float z) {
		return position.set(v, z);
	}

	public Vector3f set(Vector2dc v, float z) {
		return position.set(v, z);
	}

	public Vector3f set(Vector2ic v, float z) {
		return position.set(v, z);
	}

	public Vector3f set(float d) {
		return position.set(d);
	}

	public Vector3f set(float x, float y, float z) {
		return position.set(x, y, z);
	}

	public Vector3f set(double d) {
		return position.set(d);
	}

	public Vector3f set(double x, double y, double z) {
		return position.set(x, y, z);
	}

	public Vector3f set(float[] xyz) {
		return position.set(xyz);
	}

	public Vector3f set(ByteBuffer buffer) {
		return position.set(buffer);
	}

	public Vector3f set(int index, ByteBuffer buffer) {
		return position.set(index, buffer);
	}

	public Vector3f set(FloatBuffer buffer) {
		return position.set(buffer);
	}

	public Vector3f set(int index, FloatBuffer buffer) {
		return position.set(index, buffer);
	}

	public Vector3f setFromAddress(long address) {
		return position.setFromAddress(address);
	}

	public Vector3f setComponent(int component, float value) throws IllegalArgumentException {
		return position.setComponent(component, value);
	}

	public FloatBuffer get(FloatBuffer buffer) {
		return position.get(buffer);
	}

	public FloatBuffer get(int index, FloatBuffer buffer) {
		return position.get(index, buffer);
	}

	public ByteBuffer get(ByteBuffer buffer) {
		return position.get(buffer);
	}

	public ByteBuffer get(int index, ByteBuffer buffer) {
		return position.get(index, buffer);
	}

	public Vector3fc getToAddress(long address) {
		return position.getToAddress(address);
	}

	public Vector3f sub(Vector3fc v) {
		return position.sub(v);
	}

	public Vector3f sub(Vector3fc v, Vector3f dest) {
		return position.sub(v, dest);
	}

	public Vector3f sub(float x, float y, float z) {
		return position.sub(x, y, z);
	}

	public Vector3f sub(float x, float y, float z, Vector3f dest) {
		return position.sub(x, y, z, dest);
	}

	public Vector3f add(Vector3fc v) {
		return position.add(v);
	}

	public Vector3f add(Vector3fc v, Vector3f dest) {
		return position.add(v, dest);
	}

	public Vector3f add(float x, float y, float z) {
		return position.add(x, y, z);
	}

	public Vector3f add(float x, float y, float z, Vector3f dest) {
		return position.add(x, y, z, dest);
	}

	public Vector3f fma(Vector3fc a, Vector3fc b) {
		return position.fma(a, b);
	}

	public Vector3f fma(float a, Vector3fc b) {
		return position.fma(a, b);
	}

	public Vector3f fma(Vector3fc a, Vector3fc b, Vector3f dest) {
		return position.fma(a, b, dest);
	}

	public Vector3f fma(float a, Vector3fc b, Vector3f dest) {
		return position.fma(a, b, dest);
	}

	public Vector3f mulAdd(Vector3fc a, Vector3fc b) {
		return position.mulAdd(a, b);
	}

	public Vector3f mulAdd(float a, Vector3fc b) {
		return position.mulAdd(a, b);
	}

	public Vector3f mulAdd(Vector3fc a, Vector3fc b, Vector3f dest) {
		return position.mulAdd(a, b, dest);
	}

	public Vector3f mulAdd(float a, Vector3fc b, Vector3f dest) {
		return position.mulAdd(a, b, dest);
	}

	public Vector3f mul(Vector3fc v) {
		return position.mul(v);
	}

	public Vector3f mul(Vector3fc v, Vector3f dest) {
		return position.mul(v, dest);
	}

	public Vector3f div(Vector3fc v) {
		return position.div(v);
	}

	public Vector3f div(Vector3fc v, Vector3f dest) {
		return position.div(v, dest);
	}

	public Vector3f mulProject(Matrix4fc mat, Vector3f dest) {
		return position.mulProject(mat, dest);
	}

	public Vector3f mulProject(Matrix4fc mat, float w, Vector3f dest) {
		return position.mulProject(mat, w, dest);
	}

	public Vector3f mulProject(Matrix4fc mat) {
		return position.mulProject(mat);
	}

	public Vector3f mul(Matrix3fc mat) {
		return position.mul(mat);
	}

	public Vector3f mul(Matrix3fc mat, Vector3f dest) {
		return position.mul(mat, dest);
	}

	public Vector3f mul(Matrix3dc mat) {
		return position.mul(mat);
	}

	public Vector3f mul(Matrix3dc mat, Vector3f dest) {
		return position.mul(mat, dest);
	}

	public Vector3f mul(Matrix3x2fc mat) {
		return position.mul(mat);
	}

	public Vector3f mul(Matrix3x2fc mat, Vector3f dest) {
		return position.mul(mat, dest);
	}

	public Vector3f mulTranspose(Matrix3fc mat) {
		return position.mulTranspose(mat);
	}

	public Vector3f mulTranspose(Matrix3fc mat, Vector3f dest) {
		return position.mulTranspose(mat, dest);
	}

	public Vector3f mulPosition(Matrix4fc mat) {
		return position.mulPosition(mat);
	}

	public Vector3f mulPosition(Matrix4x3fc mat) {
		return position.mulPosition(mat);
	}

	public Vector3f mulPosition(Matrix4fc mat, Vector3f dest) {
		return position.mulPosition(mat, dest);
	}

	public Vector3f mulPosition(Matrix4x3fc mat, Vector3f dest) {
		return position.mulPosition(mat, dest);
	}

	public Vector3f mulTransposePosition(Matrix4fc mat) {
		return position.mulTransposePosition(mat);
	}

	public Vector3f mulTransposePosition(Matrix4fc mat, Vector3f dest) {
		return position.mulTransposePosition(mat, dest);
	}

	public float mulPositionW(Matrix4fc mat) {
		return position.mulPositionW(mat);
	}

	public float mulPositionW(Matrix4fc mat, Vector3f dest) {
		return position.mulPositionW(mat, dest);
	}

	public Vector3f mulDirection(Matrix4dc mat) {
		return position.mulDirection(mat);
	}

	public Vector3f mulDirection(Matrix4fc mat) {
		return position.mulDirection(mat);
	}

	public Vector3f mulDirection(Matrix4x3fc mat) {
		return position.mulDirection(mat);
	}

	public Vector3f mulDirection(Matrix4dc mat, Vector3f dest) {
		return position.mulDirection(mat, dest);
	}

	public Vector3f mulDirection(Matrix4fc mat, Vector3f dest) {
		return position.mulDirection(mat, dest);
	}

	public Vector3f mulDirection(Matrix4x3fc mat, Vector3f dest) {
		return position.mulDirection(mat, dest);
	}

	public Vector3f mulTransposeDirection(Matrix4fc mat) {
		return position.mulTransposeDirection(mat);
	}

	public Vector3f mulTransposeDirection(Matrix4fc mat, Vector3f dest) {
		return position.mulTransposeDirection(mat, dest);
	}

	public Vector3f mul(float scalar) {
		return position.mul(scalar);
	}

	public Vector3f mul(float scalar, Vector3f dest) {
		return position.mul(scalar, dest);
	}

	public Vector3f mul(float x, float y, float z) {
		return position.mul(x, y, z);
	}

	public Vector3f mul(float x, float y, float z, Vector3f dest) {
		return position.mul(x, y, z, dest);
	}

	public Vector3f div(float scalar) {
		return position.div(scalar);
	}

	public Vector3f div(float scalar, Vector3f dest) {
		return position.div(scalar, dest);
	}

	public Vector3f div(float x, float y, float z) {
		return position.div(x, y, z);
	}

	public Vector3f div(float x, float y, float z, Vector3f dest) {
		return position.div(x, y, z, dest);
	}

	public Vector3f rotate(Quaternionfc quat) {
		return position.rotate(quat);
	}

	public Vector3f rotate(Quaternionfc quat, Vector3f dest) {
		return position.rotate(quat, dest);
	}

	public Quaternionf rotationTo(Vector3fc toDir, Quaternionf dest) {
		return position.rotationTo(toDir, dest);
	}

	public Quaternionf rotationTo(float toDirX, float toDirY, float toDirZ, Quaternionf dest) {
		return position.rotationTo(toDirX, toDirY, toDirZ, dest);
	}

	public Vector3f rotateAxis(float angle, float x, float y, float z) {
		return position.rotateAxis(angle, x, y, z);
	}

	public Vector3f rotateAxis(float angle, float aX, float aY, float aZ, Vector3f dest) {
		return position.rotateAxis(angle, aX, aY, aZ, dest);
	}

	public Vector3f rotateX(float angle) {
		return position.rotateX(angle);
	}

	public Vector3f rotateX(float angle, Vector3f dest) {
		return position.rotateX(angle, dest);
	}

	public Vector3f rotateY(float angle) {
		return position.rotateY(angle);
	}

	public Vector3f rotateY(float angle, Vector3f dest) {
		return position.rotateY(angle, dest);
	}

	public Vector3f rotateZ(float angle) {
		return position.rotateZ(angle);
	}

	public Vector3f rotateZ(float angle, Vector3f dest) {
		return position.rotateZ(angle, dest);
	}

	public float lengthSquared() {
		return position.lengthSquared();
	}

	public float length() {
		return position.length();
	}

	public Vector3f normalize() {
		return position.normalize();
	}

	public Vector3f normalize(Vector3f dest) {
		return position.normalize(dest);
	}

	public Vector3f normalize(float length) {
		return position.normalize(length);
	}

	public Vector3f normalize(float length, Vector3f dest) {
		return position.normalize(length, dest);
	}

	public Vector3f cross(Vector3fc v) {
		return position.cross(v);
	}

	public Vector3f cross(float x, float y, float z) {
		return position.cross(x, y, z);
	}

	public Vector3f cross(Vector3fc v, Vector3f dest) {
		return position.cross(v, dest);
	}

	public Vector3f cross(float x, float y, float z, Vector3f dest) {
		return position.cross(x, y, z, dest);
	}

	public float distance(Vector3fc v) {
		return position.distance(v);
	}

	public float distance(float x, float y, float z) {
		return position.distance(x, y, z);
	}

	public float distanceSquared(Vector3fc v) {
		return position.distanceSquared(v);
	}

	public float distanceSquared(float x, float y, float z) {
		return position.distanceSquared(x, y, z);
	}

	public float dot(Vector3fc v) {
		return position.dot(v);
	}

	public float dot(float x, float y, float z) {
		return position.dot(x, y, z);
	}

	public float angleCos(Vector3fc v) {
		return position.angleCos(v);
	}

	public float angle(Vector3fc v) {
		return position.angle(v);
	}

	public float angleSigned(Vector3fc v, Vector3fc n) {
		return position.angleSigned(v, n);
	}

	public float angleSigned(float x, float y, float z, float nx, float ny, float nz) {
		return position.angleSigned(x, y, z, nx, ny, nz);
	}

	public Vector3f min(Vector3fc v) {
		return position.min(v);
	}

	public Vector3f min(Vector3fc v, Vector3f dest) {
		return position.min(v, dest);
	}

	public Vector3f max(Vector3fc v) {
		return position.max(v);
	}

	public Vector3f max(Vector3fc v, Vector3f dest) {
		return position.max(v, dest);
	}

	public Vector3f zero() {
		return position.zero();
	}

	@Override
	public String toString() {
		return "Transform [position=" + position + ", up=" + up + ", looking=" + looking + ", trans=" + trans
				+ ", velocity=" + velocity + ", yaw=" + yaw + ", pitch=" + pitch + ", rotatex=" + rotatex + ", rotatey="
				+ rotatey + ", rotatez=" + rotatez + ", children=" + children + ", update=" + update + "]";
	}

	public String toString(NumberFormat formatter) {
		return position.toString(formatter);
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		position.writeExternal(out);
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		position.readExternal(in);
	}

	public Vector3f negate() {
		return position.negate();
	}

	public Vector3f negate(Vector3f dest) {
		return position.negate(dest);
	}

	public Vector3f absolute() {
		return position.absolute();
	}

	public Vector3f absolute(Vector3f dest) {
		return position.absolute(dest);
	}

	@Override
	public int hashCode() {
		return position.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return position.equals(obj);
	}

	public boolean equals(Vector3fc v, float delta) {
		return position.equals(v, delta);
	}

	public boolean equals(float x, float y, float z) {
		return position.equals(x, y, z);
	}

	public Vector3f reflect(Vector3fc normal) {
		return position.reflect(normal);
	}

	public Vector3f reflect(float x, float y, float z) {
		return position.reflect(x, y, z);
	}

	public Vector3f reflect(Vector3fc normal, Vector3f dest) {
		return position.reflect(normal, dest);
	}

	public Vector3f reflect(float x, float y, float z, Vector3f dest) {
		return position.reflect(x, y, z, dest);
	}

	public Vector3f half(Vector3fc other) {
		return position.half(other);
	}

	public Vector3f half(float x, float y, float z) {
		return position.half(x, y, z);
	}

	public Vector3f half(Vector3fc other, Vector3f dest) {
		return position.half(other, dest);
	}

	public Vector3f half(float x, float y, float z, Vector3f dest) {
		return position.half(x, y, z, dest);
	}

	public Vector3f smoothStep(Vector3fc v, float t, Vector3f dest) {
		return position.smoothStep(v, t, dest);
	}

	public Vector3f hermite(Vector3fc t0, Vector3fc v1, Vector3fc t1, float t, Vector3f dest) {
		return position.hermite(t0, v1, t1, t, dest);
	}

	public Vector3f lerp(Vector3fc other, float t) {
		return position.lerp(other, t);
	}

	public Vector3f lerp(Vector3fc other, float t, Vector3f dest) {
		return position.lerp(other, t, dest);
	}

	public float get(int component) throws IllegalArgumentException {
		return position.get(component);
	}

	public Vector3i get(int mode, Vector3i dest) {
		return position.get(mode, dest);
	}

	public Vector3f get(Vector3f dest) {
		return position.get(dest);
	}

	public Vector3d get(Vector3d dest) {
		return position.get(dest);
	}

	public int maxComponent() {
		return position.maxComponent();
	}

	public int minComponent() {
		return position.minComponent();
	}

	public Vector3f orthogonalize(Vector3fc v, Vector3f dest) {
		return position.orthogonalize(v, dest);
	}

	public Vector3f orthogonalize(Vector3fc v) {
		return position.orthogonalize(v);
	}

	public Vector3f orthogonalizeUnit(Vector3fc v, Vector3f dest) {
		return position.orthogonalizeUnit(v, dest);
	}

	public Vector3f orthogonalizeUnit(Vector3fc v) {
		return position.orthogonalizeUnit(v);
	}

	public Vector3f floor() {
		return position.floor();
	}

	public Vector3f floor(Vector3f dest) {
		return position.floor(dest);
	}

	public Vector3f ceil() {
		return position.ceil();
	}

	public Vector3f ceil(Vector3f dest) {
		return position.ceil(dest);
	}

	public Vector3f round() {
		return position.round();
	}

	public Vector3f round(Vector3f dest) {
		return position.round(dest);
	}

	public boolean isFinite() {
		return position.isFinite();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return position.clone();
	}

	/**
	 * clamps this vector to stay within the borders defined
	 * 
	 * @param min
	 * @param max
	 */
	public void clampInside(Vector3f min, Vector3f max) {
		this.set(Math.max(min.x, Math.min(this.x(), max.x)), this.y(), this.z());
		this.set(this.x(), Math.max(min.y, Math.min(this.y(), max.y)), this.z());
		this.set(this.x(), this.y(), Math.max(min.z, Math.min(this.z(), max.z)));

	}

	/**
	 * This vector cannot enter the positions bounding box
	 * 
	 * @param min
	 * @param max
	 */
	public void clampOutside(Vector3f min, Vector3f max) {
		this.set(Math.min(min.x, Math.max(this.x(), max.x)), this.y(), this.z());
		this.set(this.x(), Math.min(min.y, Math.max(this.y(), max.y)), this.z());
		this.set(this.x(), this.y(), Math.min(min.z, Math.max(this.z(), max.z)));
	}

	public static Transform createTransform(Vector3f pos) {
		return new Transform(pos);
	}

	public Matrix4f configure() {
		trans.identity();
		trans.rotate(rotatex, 1, 0, 0);
		trans.rotate(rotatey, 0, 1, 0);
		trans.rotate(rotatez, 0, 0, 1);
		return trans;
	}

	public void addXrot(float ang) {
		rotatex += ang;
	}

	public void addYrot(float ang) {
		rotatey += ang;
	}

	public void addZrot(float ang) {
		rotatez += ang;
	}

	public void subXrot(float ang) {
		rotatex -= ang;
	}

	public void subYrot(float ang) {
		rotatey -= ang;
	}

	public void subZrot(float ang) {
		rotatez -= ang;
	}
}
