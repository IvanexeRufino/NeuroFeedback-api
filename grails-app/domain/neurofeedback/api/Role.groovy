package neurofeedback.api

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@EqualsAndHashCode(includes='authority')
@ToString(includes='authority', includeNames=true, includePackage=false)
class Role implements Serializable {

	private static final long serialVersionUID = 1
	int id
    String description
	String authority

	static constraints = {
		authority nullable: false, blank: false, unique: true
        id (unique: true, maxSize: 11)
        description (blank: false, maxSize: 255)
	}

	static mapping = {
		cache true
	}

	String toString() {
		return authority
	}
}
