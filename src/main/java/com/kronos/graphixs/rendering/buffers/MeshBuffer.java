/**
 * 
 */
package com.kronos.graphixs.rendering.buffers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import com.kronos.graphixs.geometry.Mesh;
import com.kronos.graphixs.shaders.ShaderProgram;

/**
 * 
 */
public class MeshBuffer<T extends Mesh> {
	ArrayList<T> data;
	ShaderProgram draw;

	public MeshBuffer(ArrayList<T> data) {
		super();
		this.data = data;
	}

	public MeshBuffer() {
		super();
		data = new ArrayList<>();
	}

	public void forEach(Consumer<? super T> action) {
		data.forEach(action);
	}

	public int size() {
		return data.size();
	}

	public boolean isEmpty() {
		return data.isEmpty();
	}

	public boolean contains(Object o) {
		return data.contains(o);
	}

	public Iterator<T> iterator() {
		return data.iterator();
	}

	public Object[] toArray() {
		return data.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return data.toArray(a);
	}

	public boolean add(T e) {
		return data.add(e);
	}

	public boolean remove(Object o) {
		return data.remove(o);
	}

	public boolean containsAll(Collection<?> c) {
		return data.containsAll(c);
	}

	public boolean addAll(Collection<? extends T> c) {
		return data.addAll(c);
	}

	public boolean addAll(int index, Collection<? extends T> c) {
		return data.addAll(index, c);
	}

	public boolean removeAll(Collection<?> c) {
		return data.removeAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		return data.retainAll(c);
	}

	public void replaceAll(UnaryOperator<T> operator) {
		data.replaceAll(operator);
	}

	public <T> T[] toArray(IntFunction<T[]> generator) {
		return data.toArray(generator);
	}

	public void sort(Comparator<? super T> c) {
		data.sort(c);
	}

	public void clear() {
		data.clear();
	}

	@Override
	public boolean equals(Object o) {
		return data.equals(o);
	}

	@Override
	public int hashCode() {
		return data.hashCode();
	}

	public T get(int index) {
		return data.get(index);
	}

	public T set(int index, T element) {
		return data.set(index, element);
	}

	public void add(int index, T element) {
		data.add(index, element);
	}

	public boolean removeIf(Predicate<? super T> filter) {
		return data.removeIf(filter);
	}

	public T remove(int index) {
		return data.remove(index);
	}

	public int indexOf(Object o) {
		return data.indexOf(o);
	}

	public int lastIndexOf(Object o) {
		return data.lastIndexOf(o);
	}

	public ListIterator<T> listIterator() {
		return data.listIterator();
	}

	public ListIterator<T> listIterator(int index) {
		return data.listIterator(index);
	}

	public List<T> subList(int fromIndex, int toIndex) {
		return data.subList(fromIndex, toIndex);
	}

	public Spliterator<T> spliterator() {
		return data.spliterator();
	}

	public Stream<T> stream() {
		return data.stream();
	}

	public Stream<T> parallelStream() {
		return data.parallelStream();
	}

	public void trimToSize() {
		data.trimToSize();
	}

	public void ensureCapacity(int minCapacity) {
		data.ensureCapacity(minCapacity);
	}

	@Override
	public Object clone() {
		return data.clone();
	}

	@Override
	public String toString() {
		return data.toString();
	}

	public ShaderProgram getDraw() {
		return draw;
	}

	public void setDraw(ShaderProgram draw) {
		this.draw = draw;
	}

	public void drawBuffer() {
		for (Iterator iterator = data.iterator(); iterator.hasNext();) {
			T t = (T) iterator.next();
			t.render(draw);
		}
	}

}
