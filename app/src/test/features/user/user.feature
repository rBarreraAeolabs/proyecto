Feature: User management
    sdadasd
    asdasda

    Scenario: Retrieve administrator user
        When I search user 'admin'
        Then the user is found
        And his last name is 'Administrator'

    Scenario: Retrieve administrator user
        When I search user 'assds'
        Then the user is not found
        And his last name is 'Administrator'


