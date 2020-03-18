##File: BrickBreaker_1.0.py
##Name: Michael Breen
##Desc: This program is a game of Brick Breaker. The player controls a paddle
##      on the bottom of the screen, and uses it to bounce a ball in motion
##      towards a series of bricks at the top of the screen. If the ball
##      strikes one of the bricks, it is 'broken' and dissapears. The ball
##      bounces off of the paddle, bricks, sides, and top of the canvas. The
##      game is won if all of the bricks are broken. The game is lost if the
##      ball passes the paddle and hits the bottom of the canvas.

from graphics import *
winW,winH = 1280,700
win = GraphWin('Brick Breaker 1.0', winW, winH)
win.setBackground('#C0C0C0')

class Block:
    pass

def getCenters(ball,obj):
    ballCtrX = ball.getCenter().getX()
    ballCtrY = ball.getCenter().getY()
    objCtrX = obj.rect.getCenter().getX()
    objCtrY = obj.rect.getCenter().getY()
    return ballCtrX,ballCtrY,objCtrX,objCtrY

def collisionX(ball,ballRad,obj):
    ballCtrX,ballCtrY,objCtrX,objCtrY = getCenters(ball,obj)
    if ( obj.x1 - ballRad <= ballCtrX <= obj.x2 + ballRad and
         obj.y1 < ballCtrY < obj.y2 ):
        return True

def collisionY(ball,ballRad,obj):
    ballCtrX,ballCtrY,objCtrX,objCtrY = getCenters(ball,obj)
    if ( obj.y1 - ballRad <= ballCtrY <= obj.y2 + ballRad and
         obj.x1 < ballCtrX < obj.x2 ):
        return True

def startGame():
    # define paddle
    paddle = Block()
    paddleW = 120
    paddleH = 40
    paddle.x1 = (winW-paddleW)/2
    paddle.x2 = (winW+paddleW)/2
    paddle.y1 = (winH*11/12) - paddleH/2
    paddle.y2 = (winH*11/12) + paddleH/2
    paddle.rect = Rectangle( Point(paddle.x1,paddle.y1), Point(paddle.x2,paddle.y2) )
    paddle.rect.setFill('#FFFFFF')
    paddle.rect.setWidth(4)
    paddle.rect.draw(win)

    # define ball
    ballRad = 15
    ball = Circle(Point(winW/2,winH/2), ballRad)
    ball.setFill('#0000FF')
    ball.setWidth(4)
    ball.draw(win)

    # define bricks
    brickW = 150
    brickH = 40
    brickStartX, brickStartY = 5,5
    brickList = []
    brickColorList = ['#FF0000', '#FF7F00', '#FFFF00', '#7FFF00']
    for i in range(4):
        for j in range(8):
            brick = Block()
            brick.x1, brick.y1 = brickStartX, brickStartY
            brick.x2, brick.y2 = brick.x1 + brickW, brick.y1 + brickH
            brick.rect = Rectangle(Point(brick.x1,brick.y1),Point(brick.x2,brick.y2))
            brick.rect.setFill(brickColorList[i])
            brick.rect.setWidth(4)
            brick.rect.draw(win)
            brickList.append(brick)
            brickStartX = brickStartX + brickW + 10
        brickStartX = 5
        brickStartY = brickStartY + brickH + 10
    bricksBroken = 0
    bricksTotal = len(brickList)

    # set initial movement speeds for paddle and ball
    paddle.dx = 0
    ball_dx = 8
    ball_dy = 9

    # play countdown message
    msgCD = Text(Point(winW/2,winH*2/5), '3')
    msgCD.setSize(24)
    msgCD.setStyle('bold')
    msgCD.setTextColor('#FF0000')
    msgCD.draw(win)
    time.sleep(1)
    msgCD.setText('2')
    time.sleep(1)
    msgCD.setText('1')
    time.sleep(1)
    msgCD.setText('GO!')

    # initialize game
    frames = 0
    while True:
        # move paddle and ball
        paddle.rect.move(paddle.dx,0)
        ball.move(ball_dx,ball_dy)

        # control paddle: Left, Right, Down
        control = win.checkKey()
        if (control == 'Left'):
            paddle.dx = -16
        elif (control == 'Right'):
            paddle.dx = 16
        elif (control == 'Down'):
            paddle.dx = 0

        # get coordinates of ball and paddle
        ballCtrX,ballCtrY,paddleCtrX,paddleCtrY = getCenters(ball,paddle)
        paddle.x1 = paddleCtrX - (paddleW/2)
        paddle.x2 = paddleCtrX + (paddleW/2)
        paddle.y1 = paddleCtrY - (paddleH/2)
        paddle.y2 = paddleCtrY + (paddleH/2)
        
        # bounce paddle off of walls
        if ( (paddle.x1 <= 0 and paddle.dx < 0) or
             (paddle.x2 >= winW and paddle.dx > 0) ):
            paddle.dx = paddle.dx * -1

        # bounce ball off of walls
        if ( (ballCtrX - ballRad <= 0 and ball_dx < 0) or
             (ballCtrX + ballRad >= winW and ball_dx > 0) ):
            ball_dx = ball_dx * -1
        if ( (ballCtrY - ballRad <= 0 and ball_dy < 0) or
             (ballCtrY + ballRad >= winH and ball_dy > 0) ):
            ball_dy = ball_dy * -1

        # bounce ball off of paddle
        if ( collisionX(ball,ballRad,paddle) ): # left or right collision
            if ( ballCtrX > paddleCtrX ): # if paddle runs into ball
                ball.move(ballRad,0) # avoid oscillating
            else:
                ball.move(-ballRad,0) # avoid oscillating
            ball_dx = ball_dx * -1
        if ( collisionY(ball,ballRad,paddle) ): # top or bottom collision
            ball_dy = ball_dy * -1

        # bounce ball off of bricks
        for brick in brickList:
            if ( collisionX(ball,ballRad,brick) ): # left or right collision
                brick.rect.undraw()
                brickList.remove(brick)
                bricksBroken = bricksBroken + 1
                ball_dx = ball_dx * -1
        for brick in brickList:
            if ( collisionY(ball,ballRad,brick) ):
                brick.rect.undraw()
                brickList.remove(brick)
                bricksBroken = bricksBroken + 1
                ball_dy = ball_dy * -1

        # end game if ball hits bottom
        if ( ballCtrY + ballRad >= winH ):
            paddle.dx,ball_dx,ball_dy = 0,0,0
            for i in win.items[:]:
                i.undraw()
            break

        # end game if all bricks are broken
        if ( bricksBroken == bricksTotal ):
            paddle.dx,ball_dx,ball_dy = 0,0,0
            for i in win.items[:]:
                i.undraw()
            break

        # play at 30 frames per second, count frames
        update(30)
        frames = frames + 1

        # remove countdown message after 1 second
        if (frames > 30):
            msgCD.undraw()
        
    return bricksBroken, bricksTotal

def titleScreen():
    # game title
    msgTitle = Text(Point(winW*1/3,winH*2/5), 'BRICK \nBREAKER')
    msgTitle.setSize(36)
    msgTitle.setStyle('bold')
    msgTitle.setTextColor('#FF0000')
    msgTitle.draw(win)

    # game author
    msgAuthor = Text(Point(winW*1/3,winH*3/5), 'Michael Breen')
    msgAuthor.setSize(18)
    msgAuthor.setTextColor('#FF0000')
    msgAuthor.draw(win)

    # game instructions
    msgInst = Text(Point(winW*2/3,winH*1/2), 'Left Arrow: Move Left \nRight Arrow: Move Right \nDown Arrow: Stop \nPress Any Key To Begin!')
    msgInst.setSize(18)
    msgInst.setTextColor('#FF0000')
    msgInst.draw(win)

    # after getKey(), remove title, author, instructions
    keyStart = win.getKey()
    msgTitle.undraw()
    msgAuthor.undraw()
    msgInst.undraw()

def main():
    titleScreen()
    
    play = True
    while (play == True):
        score,goal = startGame()

        # define result message
        msgRes = Text(Point(winW/2,winH*2/5), 'YOU WIN!')
        msgRes.setSize(36)
        msgRes.setStyle('bold')
        msgRes.setTextColor('#FF0000')

        # define score message
        msgScr = Text(Point(winW/2,winH*3/5), 'Score: ' + str(score))
        msgScr.setSize(24)
        msgScr.setStyle('bold')
        msgScr.setTextColor('#FF0000')

        # define replay message
        msgRep = Text(Point(winW/2,winH*4/5), 'Replay? (y/n)')
        msgRep.setSize(24)
        msgRep.setStyle('bold')
        msgRep.setTextColor('#FF0000')
        
        if (score != goal): # if game is not won
            msgRes.setText('YOU LOSE!')

        # show results
        msgRes.draw(win)
        msgScr.draw(win)
        msgRep.draw(win)

        # ask for replay
        keyReplay = True
        while (keyReplay != 'y' and keyReplay != 'n'):
            keyReplay = win.getKey()
        if (keyReplay == 'n'):
            play = False

        # undraw end messages
        msgRes.undraw()
        msgScr.undraw()
        msgRep.undraw()

    win.close()

main()
