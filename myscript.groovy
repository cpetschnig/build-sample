import groovy.json.JsonSlurper


def getAllResults() {
  def count = 0
  def result = [:]

  HttpURLConnection connection
  URL url = new URL('https://jsonplaceholder.typicode.com/')

  while(true) {
    def new_item

    try {
      url.set(url.protocol, url.host, url.port, '/posts/' + (count + 1).toString(), '')

      connection = url.openConnection()
      def text = connection.inputStream.text
      def json = new JsonSlurper().parseText(text)

      new_item = [:]
      new_item[json.id] = json.title
      // new_item['path'] = json.body
    }
    catch (Exception e) {
      return [e.dump()]
    }
    finally {
      connection.disconnect()
    }

    result += new_item

    count++
    if (count > 2) { break }
  }

  return result
}

return getAllResults()
