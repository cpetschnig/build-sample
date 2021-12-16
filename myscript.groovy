def mymethod() {
  try {
    Date latestdate = new Date()
    return [latestdate.getTime().toString()]
  }
  catch (Exception e) {
    return [e.dump()]
  }
}

return this
