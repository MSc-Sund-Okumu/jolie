from ..test-unit import TestUnitInterface


type TestType {
  header: string
}

interface NothingServiceInterface {
  requestResponse:
    nothing( TestType )(void)
}

service NothingService {

    inputPort TaskService2 {
      location: "local"
      interfaces: NothingServiceInterface
    }

    main {
        nothing( req )( res ) { nullProcess }
    }
}


type TokenType { token: int }

interface extender TokenExtender {
  RequestResponse:
    *( TokenType )( TokenType ) throws InvalidToken
}

service EmbedderService {

  embed NothingService as NothingService

  inputPort input {    
    location: "local"
    protocol: "sodep"
    aggregates: NothingService with TokenExtender
  }

  courier input {
    [ nothing( request )( response ){
      forward( request )( response )
      response.token = 2
    } ]
  }

  main {
    linkIn(l)
  }

}

service Main {

    embed EmbedderService as EmbedderService

    inputPort TestUnitInput {
        location: "local"
        interfaces: TestUnitInterface
    }

    main {
        test()() {
            nothing@EmbedderService({
                header = "test"
                token = 1
            })(r)
            if ( r.token != 2 ) {
              throw( TestFailed, "expect response.token to be " + 2 + ", got " + r.token )
            }
        }
    }

}

