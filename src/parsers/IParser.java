package parsers;

import data.Contacts;
/**
* All parsers to read from files must implement this functional interface
* @author  Hadi Ibrahin
* @version 1.0
*/
public interface IParser {
	Contacts readData (String path);
}
