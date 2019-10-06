package neurofeedback.api

import java.util.stream.Collectors

class Treatment {

    int id
    String name
    String description
    int sessionNumber
    List<ChannelConfig> channelsConfig

    static constraints = {
        id (unique: true, maxSize: 11)
        name (unique: true, blank: false, maxSize: 50)
        description (blank: false, maxSize: 255)
        sessionNumber (blank: false)
    }

    Set<String> getChannels() {
        channelsConfig.stream().map { ChannelConfig channel ->
            channel.channel.name
        }.collect(Collectors.toSet())
    }
    
    def toJson(){
        ["id":id,"name":name,"description":description, "sessionNumber": sessionNumber,
         "channels_config": channelsConfig.stream().map { cc -> cc.toJson()}.collect()]
    }
}
