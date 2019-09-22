package neurofeedback.api

class Treatment {

    int id
    String name
    String description

    static constraints = {
        id (unique: true, maxSize: 11)
        name (unique: true, blank: false, maxSize: 50)
        description (blank: false, maxSize: 255)
        treatmentTimestamp (blank: false,value:now())
        duration (blank: false, minValue: 0)
        effectiveness (blank: false, range: 0..100)
    }
    
    def toJson(){
        ["id":id,"name":name,"description":description,"effectiveness":effectiveness,"duration":duration]
    }
}
