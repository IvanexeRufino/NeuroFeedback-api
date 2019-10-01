package neurofeedback.api

class Treatment {

    int id
    String name
    String description
    List<ChannelConfig> channelsConfig

    static constraints = {
        id (unique: true, maxSize: 11)
        name (unique: true, blank: false, maxSize: 50)
        description (blank: false, maxSize: 255)
    }
    
    def toJson(){
        ["id":id,"name":name,"description":description, "channels_config": channelsConfig]
    }
}
