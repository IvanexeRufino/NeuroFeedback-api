package neurofeedback.api

import java.util.stream.Collectors

class Treatment {

    int id
    String name
    String description
    String longDescription
    int sessionNumber
    static hasMany = [channelsConfig: ChannelConfig]

    static constraints = {
        id (unique: true, maxSize: 11)
        name (unique: true, blank: false, maxSize: 50)
        description (blank: false, maxSize: 1023)
        longDescription (blank: false, maxSize: 1023)
        sessionNumber (blank: false)
    }

    Set<String> getChannels() {
        channelsConfig.stream().map { ChannelConfig channel ->
            channel.channel.name
        }.collect(Collectors.toSet())
    }
    
    def toJson(){
        ["name":name,"description":description, "sessionNumber": sessionNumber,
         "channels_config": channelsConfig.stream().map { cc -> cc.toJson()}.collect()]
    }
}
