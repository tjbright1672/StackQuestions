package com.grafschokula.stackquestions.ui.search

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.Menu
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.SearchView
import com.grafschokula.stackquestions.R
import com.grafschokula.stackquestions.databinding.ActivityQuestionsSearchBinding
import com.grafschokula.stackquestions.ui.base.BaseActivity
import com.grafschokula.stackquestions.utils.RxSearchObservable
import com.grafschokula.stackquestions.utils.addFragmentToActivity
import com.grafschokula.stackquestions.utils.replaceFragmentInActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class QuestionsSearchActivity : BaseActivity<ActivityQuestionsSearchBinding>(){

    @Inject
    lateinit var questionsSearchFragment: QuestionsSearchFragment

    private val disposable = CompositeDisposable()

    override fun getLayResId() = R.layout.activity_questions_search

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            addFragmentToActivity(R.id.fragment_container, questionsSearchFragment)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.serach_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = getString(R.string.search_hint)
        searchView.inputType = InputType.TYPE_TEXT_FLAG_CAP_WORDS
        searchView.imeOptions = searchView.imeOptions or EditorInfo.IME_ACTION_SEARCH or
                EditorInfo.IME_FLAG_NO_EXTRACT_UI or EditorInfo.IME_FLAG_NO_FULLSCREEN

        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        as QuestionsSearchFragment? ?: questionsSearchFragment.also {
            replaceFragmentInActivity(R.id.fragment_container, it)
        }

        disposable.add(
            RxSearchObservable.fromView(searchView)
            .debounce(300, TimeUnit.MILLISECONDS)
            .filter { text -> text.isNotEmpty()}
            .map { text -> text.toLowerCase().trim()}
            .distinctUntilChanged()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { query -> fragment.searchViewClicked(query)})


        return true

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}
