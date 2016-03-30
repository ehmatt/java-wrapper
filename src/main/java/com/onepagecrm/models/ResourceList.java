package com.onepagecrm.models;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.internal.Paginator;
import com.onepagecrm.net.ApiResource;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class ResourceList<Item extends ApiResource> extends ArrayList<Item> implements Serializable {

    private static final long serialVersionUID = -5804947087126420084L;

    protected List<Item> list;
    protected Paginator paginator;

    public ResourceList(List<Item> list) {
        this.setList(list);
        this.paginator = new Paginator();
    }

    public abstract List<Item> nextPage() throws OnePageException;

    public abstract List<Item> refresh() throws OnePageException;

    public List<Item> getList() {
        return list;
    }

    public ResourceList setList(List<Item> list) {
        this.list = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
            for (Item item : list) this.list.add(item);
        }
        return this;
    }

    public Paginator getPaginator() {
        return paginator;
    }

    public ResourceList setPaginator(Paginator paginator) {
        this.paginator = paginator;
        return this;
    }

    public ResourceList addNextPage(List<Item> list) {
        if (this.list != null && !this.list.isEmpty()) {
            if (list != null && !list.isEmpty()) {
                for (Item item : list) this.list.add(item);
            }
        }
        return this;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    public void add(int index, Item item) {
        list.add(index, item);
    }

    public boolean add(Item item) {
        return list.add(item);
    }

    public Item remove(int index) {
        return list.remove(index);
    }

    public Item set(int index, Item item) {
        list.set(index, item);
        return item;
    }

    public Item get(int index) {
        return list.get(index);
    }

    public int indexOf(Item item) {
        return list.indexOf(item);
    }

    public List<Item> subList(int start, int end) {
        return list.subList(start, end);
    }

    /**
     * Simply gets the next {@link Item} in the list.
     * <p/>
     * If at the end, jumps back to the start.
     *
     * @param currentPosition - Current position in the list.
     * @return - {@link Item} at the next position in the list.
     */
    public Item getNext(int currentPosition) throws IllegalStateException {
        if (list != null && !list.isEmpty()) {
            int length = list.size();
            int newPosition;
            if (currentPosition < (length - 1)) {
                newPosition = currentPosition + 1;
            } else {
                newPosition = 0;
            }
            return list.get(newPosition);
        } else {
            throw new IllegalStateException("Cannot get next item for list which is not initialised or empty.");
        }
    }

    /**
     * Simply gets the previous {@link Item} in the list.
     * <p/>
     * If at the start, jumps back to the end.
     *
     * @param currentPosition - Current position in list.
     * @return - {@link Item} in the previous position.
     */
    public Item getPrevious(int currentPosition) throws IllegalStateException {
        if (list != null && !list.isEmpty()) {
            int length = list.size();
            int newPosition;
            if (currentPosition > 0) {
                newPosition = currentPosition - 1;
            } else {
                newPosition = length - 1;
            }
            return list.get(newPosition);
        } else {
            throw new IllegalStateException("Cannot get previous item for list which is not initialised or empty.");
        }
    }

    /**
     * Get the list index of the given {@link Item}.
     *
     * @param item - {@link Item} whose index is being sought.
     * @return - Index of the given item, -1 if not found in list.
     */
    public int getPosition(Item item) {
        return this.indexOf(item);
    }

    @Override
    public Iterator<Item> iterator() {
        return new RangeIterator(list);
    }

    private final class RangeIterator implements Iterator<Item> {
        private List<Item> items;
        private int cursor;
        private final int end;

        public RangeIterator(List<Item> items) {
            this.items = items;
            this.cursor = 0;
            this.end = items.size();
        }

        public boolean hasNext() {
            return this.cursor < end;
        }

        public Item next() {
            if (this.hasNext()) {
                int current = cursor;
                cursor++;
                return items.get(current);
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
