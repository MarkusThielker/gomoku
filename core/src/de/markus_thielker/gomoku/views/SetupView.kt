package de.markus_thielker.gomoku.views

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.Array
import de.markus_thielker.gomoku.Application
import de.markus_thielker.gomoku.components.GomokuConfiguration
import de.markus_thielker.gomoku.components.GomokuOpening

/**
 * This screen is shown before starting a game of gomoku to setup the game configuration
 *
 * @param application works as navigation host and storage for global variables
 *
 * @author Markus Thielker
 *
 */
class SetupView(private val application : Application) : ScreenAdapter() {

    private lateinit var stageSetup : Stage

    private lateinit var btnStartGame : TextButton
    private lateinit var selectOpening : SelectBox<GomokuOpening>

    private lateinit var txtNameOne : TextField

    private lateinit var txtNameTwo : TextField

    override fun show() {

        // get input for name of p1
        txtNameOne = TextField("", application.skin)
        txtNameOne.text = "Player1"
        txtNameOne.setSize(150f, 30f)
        txtNameOne.setPosition((Gdx.graphics.width).toFloat() / 2 - 5, (Gdx.graphics.height).toFloat() / 2 + 15, Align.bottomRight)

        // get input for name of p2
        txtNameTwo = TextField("", application.skin)
        txtNameTwo.text = "Player2"
        txtNameTwo.setSize(150f, 30f)
        txtNameTwo.setPosition((Gdx.graphics.width).toFloat() / 2 + 5, (Gdx.graphics.height).toFloat() / 2 + 15, Align.bottomLeft)

        // get input for game opening rule
        selectOpening = SelectBox<GomokuOpening>(application.skin)
        selectOpening.items = Array(arrayOf(GomokuOpening.Standard, GomokuOpening.Swap2))
        selectOpening.setSize(310f, 30f)
        selectOpening.setPosition((Gdx.graphics.width).toFloat() / 2, (Gdx.graphics.height).toFloat() / 2 - 10, Align.center)

        // get input to start game
        btnStartGame = TextButton("Start the game", application.skin)
        btnStartGame.setSize(310f, 30f)
        btnStartGame.setPosition((Gdx.graphics.width).toFloat() / 2, (Gdx.graphics.height).toFloat() / 2 - 50, Align.center)
        btnStartGame.addListener(object : ClickListener() {
            override fun clicked(event : InputEvent?, x : Float, y : Float) {
                application.screen =
                    GameView(
                        application,
                        GomokuConfiguration(txtNameOne.text, txtNameTwo.text, selectOpening.selected),
                        null,
                        null
                    )
            }
        })

        // add widgets to stageSetup
        stageSetup = Stage()
        Gdx.input.inputProcessor = stageSetup

        stageSetup.addActor(txtNameOne)

        stageSetup.addActor(txtNameTwo)

        stageSetup.addActor(selectOpening)
        stageSetup.addActor(btnStartGame)
    }

    override fun render(delta : Float) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        stageSetup.act()
        stageSetup.draw()
    }

    override fun dispose() {
        stageSetup.dispose()
    }
}