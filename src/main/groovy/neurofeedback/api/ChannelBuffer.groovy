package neurofeedback.api

class ChannelBuffer {

    List<List> buffer

    ChannelBuffer() {
        this.buffer = []

        buffer.add([])
        buffer.add([])
        buffer.add([])
        buffer.add([])
        buffer.add([])
        buffer.add([])
        buffer.add([])
        buffer.add([])
    }

    def addBufferedData(List data) {
        for(int i = 0; i < data.size(); i++) {
            buffer[i] += [data[i]]
        }
    }
}
