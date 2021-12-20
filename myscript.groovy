import groovy.json.JsonSlurper

HttpURLConnection connection;

try {
  URL url = new URL('https://jsonplaceholder.typicode.com/posts/1')

  connection = url.openConnection()
  connection.setDoOutput(true)
  def text = connection.inputStream.text

  def json = new JsonSlurper().parseText(text)

  return [json.title]
}
catch (Exception e) {
  return [e.dump()]
}
finally {
  connection.disconnect()
}
