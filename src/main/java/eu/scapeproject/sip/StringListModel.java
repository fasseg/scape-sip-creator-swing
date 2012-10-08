package eu.scapeproject.sip;

import java.util.List;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

public class StringListModel implements ListModel<String> {

	private final List<String> data;

	public StringListModel(List<String> data) {
		this.data = data;
	}

	@Override
	public int getSize() {
		return data.size();
	}

	@Override
	public String getElementAt(int index) {
		return data.get(index);
	}

	@Override
	public void addListDataListener(ListDataListener l) {

	}

	@Override
	public void removeListDataListener(ListDataListener l) {

	}

}
