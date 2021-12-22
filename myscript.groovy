import groovy.json.JsonSlurper


void addItemToMap(Map map, String key, String value) {
  map[key] = value
  map['X-' + key] = 'X: ' + value
}


def getAllResults() {
  def count = 0
  def result = [:]

  HttpURLConnection connection
  URL url = new URL('https://jsonplaceholder.typicode.com/')

  while (true) {
    String optionValue
    String optionDisplay

    try {
      url.set(url.protocol, url.host, url.port, '/posts/' + (count + 1).toString(), '')

      connection = url.openConnection()
      def text = connection.inputStream.text
      def json = new JsonSlurper().parseText(text)

      optionValue = json.id
      optionDisplay = json.title
    }
    catch (Exception e) {
      return [e.dump()]
    }
    finally {
      connection.disconnect()
    }

    addItemToMap(result, optionValue, optionDisplay)

    count++
    if (count > 2) { break }
  }

  return result
}

return getAllResults()
