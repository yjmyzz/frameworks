package org.unidal.web.jsp.function;

import java.util.List;

import org.unidal.lookup.util.ReflectUtils;
import org.unidal.web.jsp.annotation.FunctionMeta;

public class FormFunction {
	@FunctionMeta(description = "Show checkbox from an object's properties in a form", example = "${w:showCheckbox('groupBy', groupBy, payload.groupBy, 'name', 'description')}")
	public static String showCheckbox(String inputName, Object item, Object selected, String valueName, String textName) {
		StringBuilder sb = new StringBuilder(256);
		Object value = ReflectUtils.invokeGetter(item, valueName);
		Object text = ReflectUtils.invokeGetter(item, textName);

		sb.append("<input type=\"checkbox\" name=\"").append(inputName).append("\" value=\"").append(value).append("\" id=\"")
				.append(value).append("\"");

		if (selected instanceof Object[]) {
			for (Object e : (Object[]) selected) {
				if (value.equals(e)) {
					sb.append(" checked");
					break;
				}
			}
		} else if (selected instanceof List) {
			for (Object e : (List<?>) selected) {
				if (value.equals(e)) {
					sb.append(" checked");
					break;
				}
			}
		} else if (value.equals(selected)) {
			sb.append(" checked");
		}

		sb.append(">");
		sb.append("<label for=\"").append(value).append("\">").append(text).append("</label>");

		return sb.toString();
	}

	@FunctionMeta(description = "Show multiple checkboxes from a list or array object's properties in a form", example = "${w:showCheckboxes('groupBy', groupBys, payload.groupBy, 'name', 'description')}")
	public static String showCheckboxes(String inputName, Object items, Object selected, String valueName, String textName) {
		StringBuilder sb = new StringBuilder();

		if (items instanceof Object[]) {
			for (Object item : (Object[]) items) {
				sb.append(showCheckbox(inputName, item, selected, valueName, textName));
				sb.append("\r\n");
			}
		} else if (items instanceof List) {
			for (Object item : (List<?>) items) {
				sb.append(showCheckbox(inputName, item, selected, valueName, textName));
				sb.append("\r\n");
			}
		} else if (items != null) {
			throw new RuntimeException("Object[] or List expected, but was: " + items);
		}

		return sb.toString();
	}

	@FunctionMeta(description = "Show radio from an object's properties in a form", example = "${w:showRadio('groupBy', groupBy, payload.groupBy, 'name', 'description')}")
	public static String showRadio(String inputName, Object item, Object selected, String valueName, String textName) {
		StringBuilder sb = new StringBuilder(256);
		Object value = ReflectUtils.invokeGetter(item, valueName);
		Object text = ReflectUtils.invokeGetter(item, textName);

		sb.append("<input type=\"radio\" name=\"").append(inputName).append("\" value=\"").append(value).append("\" id=\"")
				.append(value).append("\"");

		if (value.equals(selected)) {
			sb.append(" checked");
		}

		sb.append(">");
		sb.append("<label for=\"").append(value).append("\">").append(text).append("</label>");

		return sb.toString();
	}

	@FunctionMeta(description = "Show multiple radios from a list or array object's properties in a form", example = "${w:showRadios('groupBy', groupBys, payload.groupBy, 'name', 'description')}")
	public static String showRadios(String inputName, Object items, Object selected, String valueName, String textName) {
		StringBuilder sb = new StringBuilder();

		if (items instanceof Object[]) {
			for (Object item : (Object[]) items) {
				sb.append(showRadio(inputName, item, selected, valueName, textName));
				sb.append("\r\n");
			}
		} else if (items instanceof List) {
			for (Object item : (List<?>) items) {
				sb.append(showRadio(inputName, item, selected, valueName, textName));
				sb.append("\r\n");
			}
		} else if (items != null) {
			throw new RuntimeException("Object[] or List expected, but was: " + items);
		}

		return sb.toString();
	}
}
