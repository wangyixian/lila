package views.html.tournament

import lila.api.Context
import lila.app.templating.Environment._
import lila.app.ui.ScalatagsTemplate._
import lila.i18n.{ I18nKeys => trans }

import controllers.routes

object bits {

  def notFound()(implicit ctx: Context) =
    views.html.base.layout(
      title = trans.tournamentNotFound.txt()
    ) {
      main(cls := "page-small box box-pad")(
        h1(trans.tournamentNotFound()),
        p(trans.tournamentDoesNotExist()),
        p(trans.tournamentMayHaveBeenCanceled()),
        br,
        br,
        a(href := routes.Tournament.home())(trans.returnToTournamentsHomepage())
      )
    }

  def enterable(tours: List[lila.tournament.Tournament]) =
    table(cls := "tournaments")(
      tours map { tour =>
        tr(
          td(cls := "name")(
            a(cls := "text", dataIcon := tournamentIconChar(tour), href := routes.Tournament.show(tour.id))(
              tour.name
            )
          ),
          tour.schedule.fold(td) { s =>
            td(momentFromNow(s.at))
          },
          td(tour.durationString),
          td(dataIcon := "r", cls := "text")(tour.nbPlayers)
        )
      }
    )

  def jsI18n(implicit ctx: Context) = i18nJsObject(translations)

  private val translations = List(
    trans.standing,
    trans.starting,
    trans.tournamentIsStarting,
    trans.youArePlaying,
    trans.standByX,
    trans.tournamentPairingsAreNowClosed,
    trans.join,
    trans.withdraw,
    trans.joinTheGame,
    trans.signIn,
    trans.averageElo,
    trans.gamesPlayed,
    trans.nbPlayers,
    trans.winRate,
    trans.berserkRate,
    trans.performance,
    trans.tournamentComplete,
    trans.movesPlayed,
    trans.whiteWins,
    trans.blackWins,
    trans.draws,
    trans.nextXTournament,
    trans.viewMoreTournaments,
    trans.averageOpponent,
    trans.ratedTournament,
    trans.casualTournament
  )
}