import data.SignInCredentials
import data.Token
import ui.AccountBox
import ui.FileBox
import ui.FileView
import velvet.main.*
import velvet.structs.Position
import web.Socket
import web.requests.ListFilesRequest
import web.requests.SignInRequest
import web.requests.UploadFileRequest
import web.responses.*
import java.io.File

class Level : Velvet(Position(1500, 832)) {

    lateinit var accountBox: AccountBox
    lateinit var fileView: FileView
    val socket = Socket()
    var token: Token? = null

    override fun init() {
        accountBox = AccountBox(uiEventHandler)
        fileView = FileView(uiEventHandler)

        val firstInChainHandler = FirstInChainHandler()

        firstInChainHandler
                .addChain(SignInSuccessHandler { account,token ->
                    accountBox.setAccountName(account.username)
                    socket.sendRequest(ListFilesRequest(token))
                    this.token = token
                })
                .addChain(SignInFailedHandler { println("Sign In Failed") })
                .addChain(ListFilesHandler {
                    fileView.clearFileBoxes()
                    it.forEach{ fileView.addFileBox(FileBox(it)) }
                })
                .addChain(EndOfChainHandler())

        socket.responseHandler = firstInChainHandler

        accountBox.credentialConsumer = { socket.sendRequest(SignInRequest(it)) }
    }

    override fun update() {
        accountBox.update()
        fileView.update()

        if(fileDrop.hasFile()){
            val file = fileDrop.popFile();
            token?.let {
                socket.sendRequest(UploadFileRequest(file, it))
            }
        }
    }

    override fun render(g: VGraphics) {
        accountBox.render(g)
        fileView.render(g)
    }

    override fun onClose() { }
}

fun main() {
    Velvet.start(Level(), "Quick Drop")
}
