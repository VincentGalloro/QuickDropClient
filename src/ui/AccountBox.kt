package ui

import data.SignInCredentials
import velvet.main.VGraphics
import velvet.structs.Bounds
import velvet.structs.Vector
import velvet.velements.container.ActuatedVContainer
import velvet.velements.container.VContainer
import velvet.velements.impl.ImageElement
import velvet.velements.impl.SquareElement
import velvet.velements.impl.TextElement
import velvet.velements.interact.UIEventHandler
import java.awt.Color
import java.io.File
import javax.imageio.ImageIO

class AccountBox(uiEventHandler: UIEventHandler) {

    private val container = ActuatedVContainer(SquareElement(outlineThickness = 2.0, rounding = 20.0))
    private val profileIcon = VContainer(ImageElement(ImageIO.read(File("res/profile_icon.png"))))
    private val accountUsername = VContainer(TextElement("[Signed Out]", fontResolution = 30, _color = Color(200, 200, 200)))
    private val usernameField = InputField(uiEventHandler, "Username")
    private val passwordField = InputField(uiEventHandler, "Password")
    private val signInButton = Button("Sign In")

    var credentialConsumer: ((SignInCredentials)->Unit)? = null

    private var opened = false

    fun setAccountName(username: String) {
        (accountUsername.vElement as TextElement).text = "Signed In [ $username ]"
        (accountUsername.vElement as TextElement).color = Color.GREEN
    }

    init {
        close()

        uiEventHandler.containers.add(signInButton.container)
        uiEventHandler.containers.add(container)

        container.uiEventListener.onMousePress = { if (opened) close() else open() }

        signInButton.container.uiEventListener.onMousePress = {
            credentialConsumer?.invoke(SignInCredentials(usernameField.text, passwordField.text))
        }
    }

    fun resize(size: Vector) {
        container.layoutActuator.targetBounds = Bounds(Vector(1480, 20)).resize(size, Vector(1, 0))
    }

    fun open() {
        opened = true
        resize(Vector(450, 300))
        usernameField.container.disabled = false
        passwordField.container.disabled = false
        signInButton.container.disabled = false
    }

    fun close() {
        opened = false
        resize(Vector(260, 48))
        usernameField.container.disabled = true
        passwordField.container.disabled = true
        signInButton.container.disabled = true
    }

    fun update() {
        container.update()

        profileIcon.bounds = container.bounds.resize(Vector(48), Vector(1, 0))
        accountUsername.bounds = Bounds(container.bounds.start, profileIcon.bounds.getPos(Vector(0, 1)))
                .scale(Vector(0.9), Vector(0.5))
                .fixRatio(accountUsername.vElement?.size ?: Vector.ONE, Vector(0.5))
        usernameField.container.bounds = container.bounds.subBounds(Bounds(Vector(0.05, 0.3), Vector(0.95, 0.45)))
        passwordField.container.bounds = container.bounds.subBounds(Bounds(Vector(0.05, 0.5), Vector(0.95, 0.65)))
        signInButton.container.bounds = container.bounds.subBounds(Bounds(Vector(0.6, 0.75), Vector(0.95, 0.9)))

        usernameField.update()
        passwordField.update()
        signInButton.update()
    }

    fun render(g: VGraphics) {
        container.render(g)
        profileIcon.render(g)
        accountUsername.render(g)
        usernameField.render(g)
        passwordField.render(g)
        signInButton.render(g)
    }
}
