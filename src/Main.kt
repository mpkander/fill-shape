import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import java.util.*
import javax.imageio.ImageIO

const val pathName = "image.png"

fun main () {
    val image = ImageIO.read(File(pathName))
    val color = Color(255, 0 , 0).rgb
    fill(image, Point(130, 130), color)

    try {
        val file = File("newimage.png")
        ImageIO.write(image, "png", file)
    } catch (e: IOException) {
        println(e)
    }
}

fun fillAlternative(image: BufferedImage, seed: Point, toReplace: Int) {
    val toFind = image.getRGB(seed.x, seed.y)
    val stack = Stack<Point>()
    val maxY = image.height - 1
    val maxX = image.width - 1

    image.setRGB(seed.x, seed.y, toReplace)
    stack.push(seed)

    while (!stack.empty()) {
        val cur = stack.pop()

        if (cur.x - 1 >= 0
            && image.getRGB(cur.x - 1, cur.y) == toFind) {
            image.setRGB(cur.x - 1, cur.y, toReplace)
            stack.push(Point(cur.x - 1, cur.y))
        }

        if (cur.x + 1 <= maxX
            && image.getRGB(cur.x + 1, cur.y) == toFind) {
            image.setRGB(cur.x + 1, cur.y, toReplace)
            stack.push(Point(cur.x + 1, cur.y))
        }

        if (cur.y - 1 >= 0
            && image.getRGB(cur.x, cur.y - 1) == toFind) {
            image.setRGB(cur.x, cur.y - 1, toReplace)
            stack.push(Point(cur.x, cur.y - 1))
        }

        if (cur.y + 1 <= maxY
            && image.getRGB(cur.x, cur.y + 1) == toFind) {
            image.setRGB(cur.x, cur.y + 1, toReplace)
            stack.push(Point(cur.x, cur.y + 1))
        }
    }


}

fun fill(image: BufferedImage, seed: Point, toReplace: Int) {
    val toFind = image.getRGB(seed.x, seed.y)
    val stack = Stack<Point>()
    val maxY = image.height - 1
    val maxX = image.width - 1

    stack.push(seed)
    while (!stack.empty()) {
        val cur = stack.pop()
        if (image.getRGB(cur.x, cur.y) == toFind) {
            image.setRGB(cur.x, cur.y, toReplace)
        }

        if (cur.x - 1 >= 0
            && image.getRGB(cur.x - 1, cur.y) == toFind) {
            stack.push(Point(cur.x - 1, cur.y))
        }

        if (cur.x + 1 <= maxX
            && image.getRGB(cur.x + 1, cur.y) == toFind) {
            stack.push(Point(cur.x + 1, cur.y))
        }

        if (cur.y - 1 >= 0
            && image.getRGB(cur.x, cur.y - 1) == toFind) {
            stack.push(Point(cur.x, cur.y - 1))
        }

        if (cur.y + 1 <= maxY
            && image.getRGB(cur.x, cur.y + 1) == toFind) {
            stack.push(Point(cur.x, cur.y + 1))
        }
    }
}

class Point(
    val x: Int,
    val y: Int
)